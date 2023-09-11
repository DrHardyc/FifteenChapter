package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.api.patientonkocase.PatientOnkoCaseRequestRecord;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.domain.struct.*;
import ru.hardy.udio.repo.PeopleRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@Component
public class PeopleService {

    @Autowired
    private PeopleRepo peopleRepo;

    private final UtilService utilService = new UtilService();

    public List<People> getAll(){
        return peopleRepo.findAll();
    }


//    public List<People> search(DataFile dataFile){
//        System.out.println("Сохранение результатов");
//        List<People> peoples = new ArrayList<>();
//        People people = null;
//        for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()) {
//            System.out.println(dataFilePatient.getFIO() + " " + dataFilePatient.getEnp());
//            if (dataFilePatient.getSrz_status_code() == 1){
//                if (dataFilePatient.getEnp() != null && !dataFilePatient.getEnp().isEmpty()){
//                    people = peopleRepo.findPeopleByFamIgnoreCaseAndImIgnoreCaseAndOtIgnoreCaseAndDrAndEnp(
//                            dataFilePatient.getFam().toUpperCase(),
//                            dataFilePatient.getIm().toUpperCase(), dataFilePatient.getOt().toUpperCase(),
//                            dataFilePatient.getDr(), dataFilePatient.getEnp());
//                }
//                if (people != null){
//                    dnGetService.saveOrUpdate(dataFilePatient, people, dataFilePatientService);
//                } else {
//                    if (dataFilePatient.getEnp() != null && !dataFilePatient.getEnp().isEmpty()) {
//                        people = peopleRepo.findPeopleByEnp(dataFilePatient.getEnp());
//                    }
//                    if (people != null) {
//                        peoples.add(updateWithDFP(people, dataFilePatient));
//                        dnGetService.saveOrUpdate(dataFilePatient, people, dataFilePatientService);
//                    } else {
//                        if (dataFilePatient.getIdsrz() != null && dataFilePatient.getIdsrz() != 0L) {
//                            People newPeople = new People(dataFilePatient);
//                            peoples.add(newPeople);
//                            peopleRepo.save(newPeople);
//                            dnGetService.saveOrUpdate(dataFilePatient, newPeople, dataFilePatientService);
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("Обработка завершена");
////        peopleRepo.saveAll(peoples);
//        return peoples;
//
//    }

    public People search(PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord){
        People people;
        people = peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(
                patientOnkoCaseRequestRecord.getSurname().toUpperCase(),
                patientOnkoCaseRequestRecord.getName().toUpperCase(), patientOnkoCaseRequestRecord.getPatronymic().toUpperCase(),
                patientOnkoCaseRequestRecord.getDateBirth(), patientOnkoCaseRequestRecord.getEnp());
        if (people == null){
            people = peopleRepo.findPeopleByEnp(patientOnkoCaseRequestRecord.getEnp());
            if (people == null){
                if (searchFromSRZ(patientOnkoCaseRequestRecord)){
                    people = new People(patientOnkoCaseRequestRecord);
                    peopleRepo.save(people);
                    return people;
                } else return null;
            } else return people;
        } else return people;
    }

    public People search(ChoosingMORequestRecord choosingMORequestRecord){
        People people;
        people = peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(
                choosingMORequestRecord.getSurname().toUpperCase(),
                choosingMORequestRecord.getName().toUpperCase(), choosingMORequestRecord.getPatronymic().toUpperCase(),
                choosingMORequestRecord.getDateBirth(), choosingMORequestRecord.getEnp());
        if (people == null){
            people = peopleRepo.findPeopleByEnp(choosingMORequestRecord.getEnp());
            if (people == null){
                if (searchFromSRZ(choosingMORequestRecord)){
                    people = new People(choosingMORequestRecord);
                    peopleRepo.save(people);
                    return people;
                } else return null;
            } else return people;
        } else return people;
    }

//    private DataFile searchFromSRZ(DataFile dataFile) {
//        //System.out.println("Поиск в срз");
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
//        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
//        Statement statement = dbjdbcConfig.getSRZ();
//        for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()){
//            System.out.println(dataFilePatient.getFIO() + " " + dataFilePatient.getEnp());
//            if (dataFilePatientService.searchFromPeople(dataFilePatient)) {
//                try {
//                    ResultSet resultSet = statement.executeQuery("" +
//                            "select p.id, p.LPU from people p where p.fam = '" + dataFilePatient.getFam() +
//                            "' and p.im = '" + dataFilePatient.getIm() + "' and p.ot = '" +  dataFilePatient.getOt() +
//                            "' and p.dr = PARSE('" + dateFormat.format(dataFilePatient.getDr()) + "' as date) and p.enp = '"
//                            + dataFilePatient.getEnp() + "'");
//                    while (resultSet.next()){
//                        dataFilePatient.setIdsrz(resultSet.getLong(1));
//                        if (resultSet.getString(2) != null && !resultSet.getString(2).isEmpty()){
//                            dataFilePatient.setMo_attach(resultSet.getInt(2));
//                        }
//                    }
//                } catch(SQLException e){
//                    throw new RuntimeException(e);
//                }
//            }
//        }
//        return dataFile;
//    }

    private boolean searchFromSRZ(PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord) {
        //System.out.println("Поиск в срз");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

                try {
                    ResultSet resultSet = statement.executeQuery("" +
                            "select count(*) from people p where p.fam = '" + patientOnkoCaseRequestRecord.getSurname() +
                            "' and p.im = '" + patientOnkoCaseRequestRecord.getName() + "' and p.ot = '" +  patientOnkoCaseRequestRecord.getPatronymic() +
                            "' and p.dr = PARSE('" + dateFormat.format(patientOnkoCaseRequestRecord.getDateBirth()) + "' as date) and p.enp = '"
                            + patientOnkoCaseRequestRecord.getEnp() + "'");
                    while (resultSet.next()){
                        if (resultSet.getInt(1) >= 1) {
                            return true;
                        }
                    }
                } catch(SQLException e){
                    throw new RuntimeException(e);
                }
        return false;
    }


    private boolean searchFromSRZ(ChoosingMORequestRecord choosingMORequestRecord) {
        //System.out.println("Поиск в срз");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();

        try {
            ResultSet resultSet = statement.executeQuery("" +
                    "select count(*) from people p where p.fam = '" + choosingMORequestRecord.getSurname() +
                    "' and p.im = '" + choosingMORequestRecord.getName() + "' and p.ot = '" +  choosingMORequestRecord.getPatronymic() +
                    "' and p.dr = PARSE('" + dateFormat.format(choosingMORequestRecord.getDateBirth()) + "' as date) and p.enp = '"
                    + choosingMORequestRecord.getEnp() + "'");
            while (resultSet.next()){
                if (resultSet.getInt(1) >= 1) {
                    return true;
                }
            }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return false;
    }
    @Transactional
    public People save(People people){
        return peopleRepo.save(people);
    }

    @Transactional
    public People updateWithDFP(People people, DataFilePatient dataFilePatient){
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

    public List<People> getAlivePeople(){
        return peopleRepo.findAlivePeople();
    }

    public List<People> getAllByDNGets(){
        return peopleRepo.findAllByDNGets();
    }

    public List<People> getDNTherapistReport(int ageBeg, int ageEnd, List<String> diag, int sex, String spec){
        return peopleRepo.findDNTherapistReport(ageBeg, ageEnd, diag, sex, spec);
    }
    public List<People> getDNTherapistReport(List<String> diag, String spec){
        return peopleRepo.findDNTherapistReport(diag, spec);
    }
    public List<People> getDNTherapistReport(int age, List<String> diag, int sex, String spec){
        return peopleRepo.findDNTherapistReport(age, diag, sex, spec);
    }

    //с интервалами
    public List<People> getDNTherapistReport(int ageBeg, int ageEnd, List<String> diags, int sex, String spec, List<String> intervals){
        return peopleRepo.findDNTherapistReport(ageBeg, ageEnd, diags, sex, spec,
                utilService.transformDate(intervals.get(0), intervals.get(2), DateInterval.minDate),
                utilService.transformDate(intervals.get(1), intervals.get(3), DateInterval.maxDate));
    }
    public List<People> getDNTherapistReport(List<String> diags, String spec, List<String> intervals){
        return peopleRepo.findDNTherapistReport(diags, spec,
                utilService.transformDate(intervals.get(0), intervals.get(2), DateInterval.minDate),
                utilService.transformDate(intervals.get(1), intervals.get(3), DateInterval.maxDate));
    }
    public List<People> getDNTherapistReport(int age, List<String> diags, int sex, String spec, List<String> intervals){
        return peopleRepo.findDNTherapistReport(age, diags, sex, spec,
                utilService.transformDate(intervals.get(0), intervals.get(2), DateInterval.minDate),
                utilService.transformDate(intervals.get(1), intervals.get(3), DateInterval.maxDate));
    }
    //=========

    public List<People> getDNTherapistReportCall(int ageBeg, int ageEnd, List<String> diag, int sex, String spec){
        return peopleRepo.findDNTherapistReportCall(ageBeg, ageEnd, diag, sex, spec);
    }

    public List<People> getDNTherapistReportCall(List<String> diag, String spec){
        return peopleRepo.findDNTherapistReportCall(diag, spec);
    }

    public List<People> getDNTherapistReportCall(int age, List<String> diag, int sex, String spec){
        return peopleRepo.findDNTherapistReportCall(age, diag, sex, spec);
    }

    public List<People> getDNTherapistReportInv(int ageBeg, int ageEnd, List<String> diag, int sex, String spec){
        return peopleRepo.findDNTherapistReportInv(ageBeg, ageEnd, diag, sex, spec);
    }

    public List<People> getDNTherapistReportInv(List<String> diag, String spec){
        return peopleRepo.findDNTherapistReportInv(diag, spec);
    }

    public List<People> getDNTherapistReportInv(int age, List<String> diag, int sex, String spec){
        return peopleRepo.findDNTherapistReportInv(age, diag, sex, spec);
    }

    public List<People> getDNKardioReport(){
        return peopleRepo.findDNKardioReport();
    }

    public List<People> getDNOnkoReport(){
        return peopleRepo.findDNOnkoReport();
    }

    public People searchWithChoosingMORequestRecord(ChoosingMORequestRecord choosingMORequestRecord) {
       return peopleRepo.findPeopleBySurnameIgnoreCaseAndNameIgnoreCaseAndPatronymicIgnoreCaseAndDateBirthAndEnp(choosingMORequestRecord.getSurname(),
               choosingMORequestRecord.getName(), choosingMORequestRecord.getPatronymic(), choosingMORequestRecord.getDateBirth(),
               choosingMORequestRecord.getEnp());
    }
}
