package ru.hardy.udio.service.deamonservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchDead extends Thread {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private DNGetService dnGetService;

    @Autowired
    private DNOutService dnOutService;

    @Autowired
    private ReportTaskService reportTaskService;


//    public SearchDead(DNGetService dnGetService, DNOutService dnOutService){
//        this.dnGetService = dnGetService;
//        this.dnOutService = dnOutService;
//    }
    private List<People> search(List<People> peopleList){
        System.out.println("Поиск в срз...");
        List<People> newPeopleList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();
        int count = 0;
        int size = peopleList.size();
        try {
            for (People people : peopleList) {
                System.out.println(people.getFIO() + " " + count + "/" + size);
                count++;
                ResultSet resultSet = statement.executeQuery("select p.ds from PEOPLE p join HISTFDR h on h.pid = p.id " +
                        " where (concat(p.FAM, ' ', p.IM, ' ', p.OT) = '" + people.getFIO() + "'" +
                        " or concat(h.FAM, ' ', h.IM, ' ', h.OT) = '" + people.getFIO() + "')" +
                        " and p.DR  = PARSE('" + dateFormat.format(people.getDr()) + "' as date)" +
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
    public void run(){
        Long id = 0L;
        try {
            id = reportTaskService.add(
                    new ReportTask("Поиск умерших", "", TaskStatus.progress, "", "System", ""));

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
            long sleep = 27 - Long.parseLong(dateFormat.format(Date.from(Instant.now())));
//        try {
//            Thread.sleep(sleep * 3600L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
            int percCount = 0;
            List<People> peopleList = search(peopleService.getAlivePeople());
            System.out.println("Сохраняем результаты");
            int size = peopleList.size();
            for (People people : peopleList) {
                for (DNGet dnGet : dnGetService.getByPeopleId(people.getId())) {
                    dnOutService.add(new DNOut(dnGet, people, 1));
                }
                dnGetService.deleteAllByPeople(dnGetService.getByPeopleId(people.getId()));
                peopleService.save(people);
                System.out.println(people.getFIO() + " " + people.getDr() + " " + percCount + "/" + size);
                percCount++;
            }
        } catch (Exception err){
            if (id != 0L){
                reportTaskService.updateStatus(TaskStatus.error, err.getMessage(), "", id);
            } else err.getStackTrace();
        }
    }

}
