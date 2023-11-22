package ru.hardy.udio.service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Span;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.mapper.PeopleSRZMapper;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.domain.mapper.PeopleUDIOMapper;
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
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Component
public class PeopleService {

    @Autowired
    private PeopleRepo peopleRepo;

    @Autowired
    private DataFilePatientService dataFilePatientService;

    private final UtilService utilService = new UtilService();

    public List<People> getAll() {
        return peopleRepo.findAll();
    }

    public ResultProcessingClass<People> search(Object o) {
       InstanceObjectParam instantObject = getInstantObject(o);
        People people;
        people = peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(
                instantObject.surname().toUpperCase(),
                instantObject.name().toUpperCase(), instantObject.patronymic().toUpperCase(),
                instantObject.dateBirth(), instantObject.enp());
        if (people == null) {
            if (!instantObject.enp.isEmpty())
                people = peopleRepo.findPeopleByEnp(instantObject.enp());
            if (people == null) {
                People peopleFromSRZWithInsuredPerson = searchFromSRZWithInsuredPerson(instantObject);
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
                    People peopleFromDBWithENP = searchFromSRZWithENP(instantObject.enp());
                    if (peopleFromDBWithENP != null) {
                        people = new People(peopleFromDBWithENP);
                        peopleRepo.save(people);
                        if (peopleFromDBWithENP.getDs() == null)
                            return new ResultProcessingClass<>(1, peopleFromDBWithENP);
                        else return new ResultProcessingClass<>(2, peopleFromDBWithENP);
                    } else return null;
                }
            } else {
                People peopleFromDBWithInsurePerson = searchFromSRZWithInsuredPerson(instantObject);
                if (peopleFromDBWithInsurePerson != null) {
                    updateWithPeopleSRZ(people, peopleFromDBWithInsurePerson);
                    if (peopleFromDBWithInsurePerson.getDs() == null)
                        return new ResultProcessingClass<>(1, people);
                    else return new ResultProcessingClass<>(2, people);
                } else {
                    People peopleFromDBWithENP = searchFromSRZWithENP(instantObject.enp());
                    if (peopleFromDBWithENP != null){
                        updateWithPeopleSRZ(people, peopleFromDBWithENP);
                        if (peopleFromDBWithENP.getDs() == null)
                            return new ResultProcessingClass<>(1, people);
                        else return new ResultProcessingClass<>(2, people);
                    } else return new ResultProcessingClass<>(2, people);
                }
            }
        } else {
            People peopleFromDBWithInsurePerson = searchFromSRZWithInsuredPerson(instantObject);
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
                    insuredPerson.getName(), insuredPerson.getPatronymic(), simpleDateFormat.parse(
                            simpleDateFormat.format(insuredPerson.getDateBirth())), insuredPerson.getEnp());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private People searchFromSRZWithENP(String enp) {
        if (enp.isEmpty()) return null;
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dbjdbcConfig.getSRZDataSource());
        RowMapper<People> peopleMapper = new PeopleSRZMapper();
        List<People> peopleList =  jdbcTemplate.query(
                    "select p.fam, p.im, p.ot, p.dr, p.enp, p.w, p.id, p.ds, p.lpu from people p where p.enp = '"
                            + enp + "'", peopleMapper);
        if (!peopleList.isEmpty()){
            return peopleList.get(0);
        }
        return null;
    }

    private People searchFromSRZWithInsuredPerson(Object o) {
        InstanceObjectParam instanceObjectParam = getInstantObject(o);
        String surname = instanceObjectParam.surname;
        String enp = "";
        if (instanceObjectParam.enp.isEmpty())
            enp = "";
        else enp = "and p.enp = '" + instanceObjectParam.enp() + "'";

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dbjdbcConfig.getSRZDataSource());
        RowMapper<People> peopleMapper = new PeopleSRZMapper();
        List<People> peopleList =  jdbcTemplate.query("select p.fam, p.im, p.ot, p.dr, p.enp, p.w, p.id, p.ds, p.lpu " +
                "from people p where p.fam = '" + instanceObjectParam.surname() +
                "' and p.im = '" + instanceObjectParam.name() + "' and p.ot = '" + instanceObjectParam.patronymic() +
                "' and p.dr = PARSE('" + dateFormat.format(instanceObjectParam.dateBirth()) + "' as date) " + enp, peopleMapper);

        if (!peopleList.isEmpty()){
            return peopleList.get(0);
        }
        return null;
    }

    @NotNull
    private static InstanceObjectParam getInstantObject(Object o) {
        String surname = "";
        String name = "";
        String patronymic = "";
        Date dateBirth = null;
        String enp = "";

        if (o instanceof InsuredPerson){
            surname = ((InsuredPerson) o).getSurname();
            name = ((InsuredPerson) o).getName();
            patronymic = ((InsuredPerson) o).getPatronymic();
            dateBirth = ((InsuredPerson) o).getDateBirth();
            enp = ((InsuredPerson) o).getEnp();
        } else if (o instanceof DataFilePatient){
            surname = ((DataFilePatient) o).getFam();
            name = ((DataFilePatient) o).getIm();
            patronymic = ((DataFilePatient) o).getOt();
            dateBirth = ((DataFilePatient) o).getDr();
            enp = ((DataFilePatient) o).getEnp();
        } else {
            return (InstanceObjectParam) o;
        }
        return new InstanceObjectParam(surname, name, patronymic, dateBirth, enp);
    }

    private record InstanceObjectParam(String surname, String name, String patronymic, Date dateBirth, String enp) {
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

    public void processingFromExcel(DataFile dataFile, Span span, UI ui) {
        AtomicInteger count = new AtomicInteger();
        dataFile.getDataFilePatient().forEach(dataFilePatient -> {
            count.getAndIncrement();
            ui.access(() -> span.setText(dataFile.getName() + " | " + count + "/" + dataFile.getDataFilePatient().size() + " | " + dataFilePatient.getFam()
                    + " " + dataFilePatient.getIm() + " " + dataFilePatient.getOt()));

            ResultProcessingClass<People> resultProcessingClass = search(dataFilePatient);
            if (resultProcessingClass != null) {
                if (resultProcessingClass.getProcessingClass().getDs() != null) {
                    dataFilePatient.setDs(resultProcessingClass.getProcessingClass().getDs());
                }
                dataFilePatientService.updateStatus(dataFilePatient, 1);
                dataFilePatient.setPeople(resultProcessingClass.getProcessingClass());
                dataFilePatient.setOnko(searchFromBars(resultProcessingClass.getProcessingClass()));
                dataFilePatient.setEnp(resultProcessingClass.getProcessingClass().getEnp());
                dataFilePatientService.add(dataFilePatient);

            } else {
                dataFilePatientService.updateStatus(dataFilePatient, 6);
                dataFilePatientService.add(dataFilePatient);
            }
        });

    }

    private int searchFromBars(People people) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dbjdbcConfig.getBarsDataSource());

        return jdbcTemplate.queryForObject("select count(*) from tf_proc.tp_casebill cb " +
                "inner join tf_proc.tp_casebill_patient cbp on cb.pid = cbp.id " +
                "left join nsi_med.med_mkb10 mkb10 on mkb10.id = cb.ds1 " +
                "where (substr(mkb10.mkb_code, 1, 1) = 'C' or substr(mkb10.mkb_code, 1, 3) between 'D00' and 'D09') " +
                "and cbp.pac_fam = '" + people.getSurname().toUpperCase() +
                "' and cbp.pac_im = '" + people.getName().toUpperCase() +
                "' and cbp.pac_ot = '" + people.getPatronymic().toUpperCase() +
                "' and cbp.pac_dr = to_date('" + dateFormat.format(people.getDateBirth()) + "', 'dd.mm.yyyy')" +
                " and cb.enp = '" + people.getEnp() + "'", Integer.class);
    }

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
                peopleList.add(new PeopleUDIOMapper().mapRow(resultSet, rowNum));
                rowNum++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peopleList;
    }
}
