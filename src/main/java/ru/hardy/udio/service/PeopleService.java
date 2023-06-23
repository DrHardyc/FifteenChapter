package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.*;
import ru.hardy.udio.repo.PeopleRepo;
import ru.hardy.udio.service.SRZ.DBFSearchService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Component
public class PeopleService {

    @Autowired
    private DataFileService dataFileService;
    @Autowired
    private PeopleRepo peopleRepo;

    @Autowired
    private DBFSearchService dbfSearchService;

    @Autowired
    private DataUdioRespService dataUdioRespService;

    @Autowired
    private DataUdioRespIdentyService dataUdioRespIdentyService;

    @Autowired
    private DNGetService dnGetService;

    @Autowired
    private DNOutService dnOutService;

    @Autowired
    private DataFilePatientService dataFilePatientService;

    public List<People> getAll(){
        return peopleRepo.findAll();
    }


    public List<People> searchFromUdio(DataFile dataFile){
        System.out.println("Сохранение результатов");
        List<People> peoples = new ArrayList<>();
        People people = null;
        for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()) {
            System.out.println(dataFilePatient.getFIO() + " " + dataFilePatient.getEnp());
            if (dataFilePatient.getSrz_status_code() == 1){
                if (dataFilePatient.getEnp() != null && !dataFilePatient.getEnp().isEmpty()){
                    people = peopleRepo.findPeopleByFamIgnoreCaseAndImIgnoreCaseAndOtIgnoreCaseAndDrAndEnp(dataFilePatient.getFam().toUpperCase(),
                            dataFilePatient.getIm().toUpperCase(), dataFilePatient.getOt().toUpperCase(),
                            dataFilePatient.getDr(), dataFilePatient.getEnp());
                }
                if (people != null){
                    dnGetService.saveOrUpdate(dataFilePatient, people, dataFilePatientService);
                } else {
                    if (dataFilePatient.getEnp() != null && !dataFilePatient.getEnp().isEmpty()) {
                        people = peopleRepo.findPeopleByEnp(dataFilePatient.getEnp());
                    }
                    if (people != null) {
                        peoples.add(updateWithDFP(people, dataFilePatient));
                        dnGetService.saveOrUpdate(dataFilePatient, people, dataFilePatientService);
                    } else {
                        if (dataFilePatient.getIdsrz() != null && dataFilePatient.getIdsrz() != 0L) {
                            People newPeople = new People(dataFilePatient);
                            peoples.add(newPeople);
                            peopleRepo.save(newPeople);
                            dnGetService.saveOrUpdate(dataFilePatient, newPeople, dataFilePatientService);
                        }
                    }
                }
            }
        }
        System.out.println("Обработка завершена");
//        peopleRepo.saveAll(peoples);
        return peoples;

    }

    public DataFile searchFromSRZ(DataFile dataFile) {
        System.out.println("Поиск в срз");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();
        for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()){
            System.out.println(dataFilePatient.getFIO() + " " + dataFilePatient.getEnp());
            if (dataFilePatientService.searchFromPeople(dataFilePatient)) {
                try {
                    ResultSet resultSet = statement.executeQuery("" +
                            "select p.id, p.LPU from people p where p.fam = '" + dataFilePatient.getFam() +
                            "' and p.im = '" + dataFilePatient.getIm() + "' and p.ot = '" +  dataFilePatient.getOt() +
                            "' and p.dr = PARSE('" + dateFormat.format(dataFilePatient.getDr()) + "' as date) and p.enp = '"
                            + dataFilePatient.getEnp() + "'");
                    while (resultSet.next()){
                        dataFilePatient.setIdsrz(resultSet.getLong(1));
                        if (resultSet.getString(2) != null && !resultSet.getString(2).isEmpty()){
                            dataFilePatient.setMo_attach(resultSet.getInt(2));
                        }
                    }
                } catch(SQLException e){
                    throw new RuntimeException(e);
                }
            }
        }
        return dataFile;
    }

    @Transactional
    public People save(People people){
        return peopleRepo.save(people);
    }

    @Transactional
    public People updateWithDFP(People people, DataFilePatient dataFilePatient){
        people.setFam(dataFilePatient.getFam());
        people.setIm(dataFilePatient.getIm());
        people.setOt(dataFilePatient.getOt());
        people.setDr(dataFilePatient.getDr());
        people.setDate_edit(Date.from(Instant.now()));
        return save(people);
    }

    public void processingFromAPI(DataFile dataFile) {
        dataFileService.save(dataFile);
            dataUdioRespIdentyService.updateProcessEnd(
                dataUdioRespService.getListDataUdioFromPeoples(
                    searchFromUdio(
                        searchFromSRZ(dataFile))));
    }

    public void processingFromExcel(DataFile dataFile) {
        dataFileService.save(dataFile);
        searchFromUdio(dataFile);
    }

    public void processingFromBars(List<DataFile> dataFileList){
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (DataFile dataFile : dataFileList) {
            Runnable worker = new Thread(() -> {
                try {
                    dataUdioRespService.getListDataUdioFromPeoples(searchFromUdio(searchFromSRZ(dataFile)));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Processing complete!");
    }

    public List<People> getAlivePeople(){
        return peopleRepo.findAlivePeople();
    }

    public List<People> getAllByDNGets(){
        return peopleRepo.findAllByDNGets();
    }
}
