package ru.hardy.udio.service;

import com.vaadin.flow.component.datepicker.DatePicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.PeopleRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Component
public class PeopleService {

    @Autowired
    private PeopleRepo peopleRepo;

    private final UtilService utilService = new UtilService();

    public List<People> getAll() {
        return peopleRepo.findAll();
    }

    public People search(InsuredPerson insuredPerson) {
        People people;
        people = peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(
                insuredPerson.getSurname().toUpperCase(),
                insuredPerson.getName().toUpperCase(), insuredPerson.getPatronymic().toUpperCase(),
                insuredPerson.getDateBirth(), insuredPerson.getEnp());
        if (people == null) {
            people = peopleRepo.findPeopleByEnp(insuredPerson.getEnp());
            if (people == null) {
                int sex = searchFromSRZ(insuredPerson);
//                System.out.println("Поиск cрз : тестовый режим");
//                int sex = 1;
                if (sex > 0) {
                    people = new People(insuredPerson);
                    people.setSex(sex);
                    peopleRepo.save(people);
                    return people;
                } else return null;
            } else return people;
        } else return people;
    }

    private int searchFromSRZ(InsuredPerson insuredPerson) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        try {
            ResultSet resultSet = statement.executeQuery(
                    "select p.w from people p where p.fam = '" + insuredPerson.getSurname() +
                            "' and p.im = '" + insuredPerson.getName() + "' and p.ot = '" + insuredPerson.getPatronymic() +
                            "' and p.dr = PARSE('" + dateFormat.format(insuredPerson.getDateBirth()) + "' as date) and p.enp = '"
                            + insuredPerson.getEnp() + "' and p.DS is null");
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Transactional
    public People save(People people) {
        return peopleRepo.save(people);
    }

    @Transactional
    public People updateWithDFP(People people, DataFilePatient dataFilePatient) {
        people.setSurname(dataFilePatient.getFam());
        people.setName(dataFilePatient.getIm());
        people.setPatronymic(dataFilePatient.getOt());
        people.setDateBirth(dataFilePatient.getDr());
        people.setDate_edit(Date.from(Instant.now()));
        return save(people);
    }

//    public void processingFromAPI(DataFile dataFile) {
//        dataFileService.save(dataFile);
//            dataUdioRespIdentyService.updateProcessEnd(
//                dataUdioRespService.getListDataUdioFromPeoples(
//                    search(
//                        searchFromSRZ(dataFile))));
//    }

//    public void processingFromExcel(DataFile dataFile) {
//        dataFileService.save(dataFile);
//        search(dataFile);
//    }

//    public void processingFromBars(List<DataFile> dataFileList){
//        ExecutorService executor = Executors.newFixedThreadPool(3);
//        for (DataFile dataFile : dataFileList) {
//            Runnable worker = new Thread(() -> {
//                try {
//                    dataUdioRespService.getListDataUdioFromPeoples(search(searchFromSRZ(dataFile)));
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            executor.execute(worker);
//        }
//        executor.shutdown();
//        while (!executor.isTerminated()) {
//        }
//        System.out.println("Processing complete!");
//    }

    public List<People> getAlivePeople() {
        return peopleRepo.findAlivePeople();
    }

    public List<People> getAllByDNGets() {
        return peopleRepo.findAllByDNGets();
    }

    public List<People> getDNTherapistReport(int ageBeg, int ageEnd, List<String> diag, int sex, String spec) {
        return peopleRepo.findDNTherapistReport(ageBeg, ageEnd, diag, sex, spec);
    }

    public List<People> getDNTherapistReport(List<String> diag, String spec) {
        return peopleRepo.findDNTherapistReport(diag, spec);
    }

    public List<People> getDNTherapistReport(int age, List<String> diag, int sex, String spec) {
        return peopleRepo.findDNTherapistReport(age, diag, sex, spec);
    }

    //с интервалами
    public List<People> getDNTherapistReport(int ageBeg, int ageEnd, List<String> diags, int sex, String spec, List<String> intervals) {
        return peopleRepo.findDNTherapistReport(ageBeg, ageEnd, diags, sex, spec,
                utilService.transformDate(intervals.get(0), intervals.get(2), DateInterval.minDate),
                utilService.transformDate(intervals.get(1), intervals.get(3), DateInterval.maxDate));
    }

    public List<People> getDNTherapistReport(List<String> diags, String spec, List<String> intervals) {
        return peopleRepo.findDNTherapistReport(diags, spec,
                utilService.transformDate(intervals.get(0), intervals.get(2), DateInterval.minDate),
                utilService.transformDate(intervals.get(1), intervals.get(3), DateInterval.maxDate));
    }

    public List<People> getDNTherapistReport(int age, List<String> diags, int sex, String spec, List<String> intervals) {
        return peopleRepo.findDNTherapistReport(age, diags, sex, spec,
                utilService.transformDate(intervals.get(0), intervals.get(2), DateInterval.minDate),
                utilService.transformDate(intervals.get(1), intervals.get(3), DateInterval.maxDate));
    }
    //=========

    public List<People> getDNTherapistReportCall(int ageBeg, int ageEnd, List<String> diag, int sex, String spec) {
        return peopleRepo.findDNTherapistReportCall(ageBeg, ageEnd, diag, sex, spec);
    }

    public List<People> getDNTherapistReportCall(List<String> diag, String spec) {
        return peopleRepo.findDNTherapistReportCall(diag, spec);
    }

    public List<People> getDNTherapistReportCall(int age, List<String> diag, int sex, String spec) {
        return peopleRepo.findDNTherapistReportCall(age, diag, sex, spec);
    }

    public List<People> getDNTherapistReportInv(int ageBeg, int ageEnd, List<String> diag, int sex, String spec) {
        return peopleRepo.findDNTherapistReportInv(ageBeg, ageEnd, diag, sex, spec);
    }

    public List<People> getDNTherapistReportInv(List<String> diag, String spec) {
        return peopleRepo.findDNTherapistReportInv(diag, spec);
    }

    public List<People> getDNTherapistReportInv(int age, List<String> diag, int sex, String spec) {
        return peopleRepo.findDNTherapistReportInv(age, diag, sex, spec);
    }

    public List<People> getDNKardioReport() {
        return peopleRepo.findDNKardioReport();
    }

    public List<People> getDNOnkoReport() {
        return peopleRepo.findDNOnkoReport();
    }

    public People searchWithChoosingMORequestRecord(ChoosingMORequestRecord choosingMORequestRecord) {
        return peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(choosingMORequestRecord.getSurname(),
                choosingMORequestRecord.getName(), choosingMORequestRecord.getPatronymic(), choosingMORequestRecord.getDateBirth(),
                choosingMORequestRecord.getEnp());
    }

    public People geByID(long id) {
        return peopleRepo.findPeopleById(id);
    }

    public List<People> getAllPeopleByJDBC(String surname, String name, String patronymic, DatePicker dateBirth,
                                              String enp) {
        List<People> peopleList = new ArrayList<>();
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getUDIO();
        String sql = "";
        ResultSet resultSet;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            if (surname.isEmpty() && name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() == null
                    && enp.isEmpty()){
                resultSet = statement.executeQuery("select p.surname, p.name, p.patronymic, p.date_birth, p.enp " +
                        "from udio_tfoms.people p ");
            } else {
                if (!surname.isEmpty())
                    sql = sql + " p.surname = '" + surname + "' and ";
                if (!name.isEmpty())
                    sql = sql + "p.name = '" + name + "' and ";
                if (!patronymic.isEmpty())
                    sql = sql + "p.patronymic = '" + patronymic + "' and ";
                if (dateBirth.getValue() != null)
                    sql = sql + "p.date_birth = to_date('" + dateFormat.format(dateBirth.getValue()) + "', 'dd.mm.yyyy') and ";
                if (!enp.isEmpty())
                    sql = sql + "p.enp = '" + enp + "' and ";
                resultSet = statement.executeQuery("select p.surname, p.name, p.patronymic, p.date_birth, p.enp " +
                        "from udio_tfoms.people p where " + sql + "1 = 1");
            }
            while (resultSet.next()){
                peopleList.add(search(new People(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return peopleList;
    }

}
