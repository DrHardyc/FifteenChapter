package ru.hardy.udio.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionLazyDelegator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.*;
import ru.hardy.udio.repo.PeopleRepo;
import ru.hardy.udio.service.SRZ.DBFSearchService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private DataFilePatientService dataFilePatientService;

    public List<People> getAll(){
        return peopleRepo.findAll();
    }

    public List<People> searchFromUdio(DataFile dataFile){
        List<People> peoples = new ArrayList<>();
        People people;
        for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()) {
            people = peopleRepo.findPeopleByFamAndImAndOtAndDrAndEnp(dataFilePatient.getFam(),
                    dataFilePatient.getIm(), dataFilePatient.getOt(),
                    dataFilePatient.getDr(), dataFilePatient.getEnp());
            if (people != null){
                dnGetService.saveOrUpdate(dataFilePatient, people);
            } else {
                people = peopleRepo.findPeopleByEnp(dataFilePatient.getEnp());
                if (people != null) {
                    peoples.add(update(people));
                    dnGetService.saveOrUpdate(dataFilePatient, people);
                }
                else {
                    if (dataFilePatient.getIdsrz() != null && dataFilePatient.getIdsrz() != 0L) {
                        People newPeople = new People(dataFilePatient);
                        peoples.add(newPeople);
                        dnGetService.saveOrUpdate(dataFilePatient, newPeople);
                    }
                }
            }
        }
        peopleRepo.saveAll(peoples);
        return peoples;

    }

    private DataFile searchFromSRZ(DataFile dataFile) {
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();
        for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()){
            if (dataFilePatientService.searchFromPeople(dataFilePatient)) {
                try {
                    ResultSet resultSet = statement.executeQuery("" +
                            "select p.id, p.LPU from people p where p.fam = '" + dataFilePatient.getFam() +
                            "' and p.im = '" + dataFilePatient.getIm() + "' and p.ot = '" +  dataFilePatient.getOt() +
                            "' and p.dr = PARSE('" + dataFilePatient.getDr() + "' as date) and p.enp = '" + dataFilePatient.getEnp() + "'");
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
       // return dbfSearchService.getDataFromDBF(dataFile);
    }

    @Transactional
    public People save(People people){
        return peopleRepo.save(people);
    }

    @Transactional
    public People update(People people){
        people.setDate_edit(Date.from(Instant.now()));
        return save(people);
    }

    public void processingFromAPI(DataFile dataFile, Long id) {
        dataFileService.save(dataFile);
            dataUdioRespIdentyService.updateProcessEnd(
                dataUdioRespService.getListDataUdioFromPeoples(
                    searchFromUdio(
                        searchFromSRZ(dataFile))), id);
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
}
