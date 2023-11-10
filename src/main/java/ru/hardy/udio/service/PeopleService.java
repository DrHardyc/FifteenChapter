package ru.hardy.udio.service;

import com.vaadin.flow.component.datepicker.DatePicker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.domain.struct.dto.PeopleDTO;
import ru.hardy.udio.repo.PeopleRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
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

    public ResultProcessingClass<People> search(InsuredPerson insuredPerson) {
        People people;
        people = peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(
                insuredPerson.getSurname().toUpperCase(),
                insuredPerson.getName().toUpperCase(), insuredPerson.getPatronymic().toUpperCase(),
                insuredPerson.getDateBirth(), insuredPerson.getEnp());
        if (people == null) {
            people = peopleRepo.findPeopleByEnp(insuredPerson.getEnp());
            if (people == null) {
                People peopleFromSRZWithInsuredPerson = searchFromSRZWithInsuredPerson(insuredPerson);
//                System.out.println("Поиск cрз : тестовый режим");
//                int sex = 1;
                if (peopleFromSRZWithInsuredPerson != null) {
                    people = new People(peopleFromSRZWithInsuredPerson.getSurname(), peopleFromSRZWithInsuredPerson.getName(),
                            peopleFromSRZWithInsuredPerson.getPatronymic(), peopleFromSRZWithInsuredPerson.getDateBirth(),
                            peopleFromSRZWithInsuredPerson.getEnp(), peopleFromSRZWithInsuredPerson.getSex(),
                            peopleFromSRZWithInsuredPerson.getIdsrz(), peopleFromSRZWithInsuredPerson.getDs(),
                            peopleFromSRZWithInsuredPerson.getMo_attach());
                    peopleRepo.save(people);
                    if (peopleFromSRZWithInsuredPerson.getDs() == null){
                        return new ResultProcessingClass<>(1, people);
                    } else {
                        return new ResultProcessingClass<>(2, people);
                    }
                } else{
                    People peopleFromDBWithENP = searchFromSRZWithENP(insuredPerson.getEnp());
                    if (peopleFromDBWithENP != null) {
                        people = new People(peopleFromDBWithENP);
                        peopleRepo.save(people);
                        if (peopleFromDBWithENP.getDs() == null)
                            return new ResultProcessingClass<>(1, peopleFromDBWithENP);
                        else return new ResultProcessingClass<>(2, peopleFromDBWithENP);
                    } else return null;
                }
            } else {
                People peopleFromDBWithInsurePerson = searchFromSRZWithInsuredPerson(insuredPerson);
                if (peopleFromDBWithInsurePerson != null) {
                    updateWithPeopleSRZ(people, peopleFromDBWithInsurePerson);
                    if (peopleFromDBWithInsurePerson.getDs() == null)
                        return new ResultProcessingClass<>(1, people);
                    else return new ResultProcessingClass<>(2, people);
                } else {
                    People peopleFromDBWithENP = searchFromSRZWithENP(insuredPerson.getEnp());
                    if (peopleFromDBWithENP != null){
                        updateWithPeopleSRZ(people, peopleFromDBWithENP);
                        if (peopleFromDBWithENP.getDs() == null)
                            return new ResultProcessingClass<>(1, people);
                        else return new ResultProcessingClass<>(2, people);
                    } else return new ResultProcessingClass<>(2, people);
                }
            }
        } else {
            People peopleFromDBWithInsurePerson = searchFromSRZWithInsuredPerson(insuredPerson);
            if (peopleFromDBWithInsurePerson != null) {
                if (people.getDs() == null && peopleFromDBWithInsurePerson.getDs() == null)
                    return new ResultProcessingClass<>(1, people);
                else return new ResultProcessingClass<>(2, people);
            } return new ResultProcessingClass<>(2, people);
        }
    }

    public People getByInsuredPerson(InsuredPerson insuredPerson) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(insuredPerson.getSurname(),
                    insuredPerson.getName(), insuredPerson.getPatronymic(), simpleDateFormat.parse(simpleDateFormat.format(insuredPerson.getDateBirth())), insuredPerson.getEnp());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private People searchFromSRZWithENP(String enp) {
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        try {
            ResultSet resultSet = statement.executeQuery(
                    "select p.fam, p.im, p.ot, p.dr, p.enp, p.w, p.id, p.ds, p.lpu from people p where p.enp = '"
                            + enp + "'");
            if (resultSet.next()) {
                return new People(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5),
                        resultSet.getInt(6), resultSet.getLong(7), resultSet.getDate(8),
                        resultSet.getInt(9));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private People searchFromSRZWithInsuredPerson(InsuredPerson insuredPerson) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        try {
            ResultSet resultSet = statement.executeQuery(
                    "select p.fam, p.im, p.ot, p.dr, p.enp, p.w, p.id, p.ds, p.lpu from people p where p.fam = '" + insuredPerson.getSurname() +
                            "' and p.im = '" + insuredPerson.getName() + "' and p.ot = '" + insuredPerson.getPatronymic() +
                            "' and p.dr = PARSE('" + dateFormat.format(insuredPerson.getDateBirth()) + "' as date) and p.enp = '"
                            + insuredPerson.getEnp() + "'");
            if (resultSet.next()) {
                return new People(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5),
                        resultSet.getInt(6), resultSet.getLong(7), resultSet.getDate(8),
                        resultSet.getInt(9));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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
        people.setDateEdit(Date.from(Instant.now()));
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

    public List<People> getAllPeopleByNotEmptyField(String surname, String name, String patronymic, DatePicker dateBirth,
                                                    String enp) {
        if (!surname.isEmpty() && name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() == null && enp.isEmpty())
            return peopleRepo.findAllBySurname(surname);
        else if (!surname.isEmpty() && !name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() == null && enp.isEmpty())
            return peopleRepo.findAllBySurnameAndName(surname, name);
        else if (!surname.isEmpty() && !name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() == null && enp.isEmpty())
            return peopleRepo.findAllBySurnameAndNameAndPatronymic(surname, name, patronymic);
        else if (!surname.isEmpty() && !name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() != null && enp.isEmpty())
            return peopleRepo.findAllBySurnameAndNameAndPatronymicAndDateBirth(surname, name, patronymic,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        else if (!surname.isEmpty() && !name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() != null && !enp.isEmpty())
            return peopleRepo.findAllBySurnameAndNameAndPatronymicAndDateBirthAndEnp(surname, name, patronymic,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), enp);
        else if (!surname.isEmpty() && name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() != null && !enp.isEmpty())
            return peopleRepo.findAllBySurnameAndPatronymicAndDateBirthAndEnp(surname, patronymic,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), enp);
        else if (!surname.isEmpty() && name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() != null && !enp.isEmpty())
            return peopleRepo.findAllBySurnameAndDateBirthAndEnp(surname,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), enp);
        else if (!surname.isEmpty() && name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() == null && !enp.isEmpty())
            return peopleRepo.findAllBySurnameAndEnp(surname, enp);
        else if (!surname.isEmpty() && name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() == null && enp.isEmpty())
            return peopleRepo.findAllBySurnameAndPatronymic(surname, patronymic);
        else if (!surname.isEmpty() && name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() != null && enp.isEmpty())
            return peopleRepo.findAllBySurnameAndDateBirth(surname,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        else if (surname.isEmpty() && !name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() == null && enp.isEmpty())
            return peopleRepo.findAllByName(name);
        else if (surname.isEmpty() && !name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() == null && enp.isEmpty())
            return peopleRepo.findAllByNameAndPatronymic(name, patronymic);
        else if (surname.isEmpty() && !name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() != null && enp.isEmpty())
            return peopleRepo.findAllByNameAndPatronymicAndDateBirth(name, patronymic,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        else if (surname.isEmpty() && !name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() != null && !enp.isEmpty())
            return peopleRepo.findAllByNameAndPatronymicAndDateBirthAndEnp(name, patronymic,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), enp);
        else if (surname.isEmpty() && !name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() != null && !enp.isEmpty())
            return peopleRepo.findAllByNameAndDateBirthAndEnp(name,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), enp);
        else if (surname.isEmpty() && !name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() == null && !enp.isEmpty())
            return peopleRepo.findAllByNameAndEnp(name, enp);
        else if (surname.isEmpty() && !name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() != null && enp.isEmpty())
            return peopleRepo.findAllByNameAndDateBirth(name, Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        else if (surname.isEmpty() && name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() == null && enp.isEmpty())
            return peopleRepo.findAllByPatronymic(patronymic);
        else if (surname.isEmpty() && name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() != null && enp.isEmpty())
            return peopleRepo.findAllByPatronymicAndDateBirth(patronymic, Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        else if (surname.isEmpty() && name.isEmpty() && !patronymic.isEmpty() && dateBirth.getValue() != null && !enp.isEmpty())
            return peopleRepo.findAllByPatronymicAndDateBirthAndEnp(patronymic,
                    Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), enp);
        else if (surname.isEmpty() && name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() != null && enp.isEmpty())
            return peopleRepo.findAllByDateBirth(Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        else if (surname.isEmpty() && name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() != null && !enp.isEmpty())
            return peopleRepo.findAllByDateBirthAndEnp(Date.from(dateBirth.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), enp);
        else if (surname.isEmpty() && name.isEmpty() && patronymic.isEmpty() && dateBirth.getValue() != null && !enp.isEmpty())
            return peopleRepo.findAllByEnp(enp);
        else
            return peopleRepo.findAll();
    }

    public void updateWithPeopleSRZ(People people, People peopleFromSRZ){
        people.setSurname(peopleFromSRZ.getSurname());
        people.setName(peopleFromSRZ.getName());
        people.setPatronymic(peopleFromSRZ.getPatronymic());
        people.setDateBirth(peopleFromSRZ.getDateBirth());
        people.setEnp(peopleFromSRZ.getEnp());
        peopleRepo.save(people);
    };

    public List<People> getPeopleWithJDBC() {
        List<People> peopleList = new ArrayList<>();
        Statement statement = new DBJDBCConfig().getUDIO();
        try {
            ResultSet resultSet = statement.executeQuery("select * from udio_tfoms.people p where p.surname = 'Премудрая'");
            int rowNum = 1;
            while (resultSet.next()){
                peopleList.add(new PeopleDTO().mapRow(resultSet, rowNum));
                rowNum++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peopleList;
    }
}
