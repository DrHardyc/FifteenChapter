package ru.hardy.udio.service.deamonservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.DNOut;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.domain.task.ReportTask;
import ru.hardy.udio.domain.task.TaskStatus;
import ru.hardy.udio.service.DNGetService;
import ru.hardy.udio.service.DNOutService;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.taskservice.ReportTaskService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchDead extends Thread {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private DNGetService dnGetService;

    @Autowired
    private DNOutService dnOutService;

    @Autowired
    private ReportTaskService reportTaskService;

    private static final String CRON = "0 0 23 * * *";

    private List<People> search(List<People> peopleList){
        List<People> newPeopleList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();
        try {
            for (People people : peopleList) {
                ResultSet resultSet = statement.executeQuery("select p.ds from PEOPLE p join HISTFDR h on h.pid = p.id " +
                        " where (concat(p.FAM, ' ', p.IM, ' ', p.OT) = '" + people.getFIO() + "'" +
                        " or concat(h.FAM, ' ', h.IM, ' ', h.OT) = '" + people.getFIO() + "')" +
                        " and p.DR  = PARSE('" + dateFormat.format(people.getDateBirth()) + "' as date)" +
                        " and coalesce(p.ds, 0) <> 0");
                if (resultSet.next()){
                    people.setDs(resultSet.getDate(1));
                    newPeopleList.add(people);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newPeopleList;
    }

    @Transactional
    @Scheduled(cron = CRON)
    public void start(){
        Long id = 0L;
        try {
            id = reportTaskService.add(
                    new ReportTask("Поиск умерших", "", TaskStatus.progress, "", "System", ""));

            List<People> peopleList = search(peopleService.getAlivePeople());
            System.out.println("Сохраняем результаты");
            for (People people : peopleList) {
                for (DNGet dnGet : dnGetService.getByPeopleId(people.getId())) {
                    dnOutService.add(new DNOut(dnGet, people, 1));
                }
                dnGetService.deleteAllByPeople(dnGetService.getByPeopleId(people.getId()));
                peopleService.save(people);
            }
        } catch (Exception err){
            if (id != 0L){
                reportTaskService.updateStatus(TaskStatus.error, err.getMessage(), "", id);
            } else err.getStackTrace();
        }
        reportTaskService.updateStatus(TaskStatus.success, "Успешно", "", id);
    }

}
