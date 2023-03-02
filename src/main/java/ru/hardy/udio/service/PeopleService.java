package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.PeopleRepo;
import ru.hardy.udio.service.SRZ.DBFSearchService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    public List<People> getAll(){
        return peopleRepo.findAll();
    }

    public List<People> searchFromUdio(DataFile dataFile){
        List<People> peoples = new ArrayList<>();
        for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()) {
            if (dataFilePatient.getIdsrz() != null) {
                People people = peopleRepo.findPeopleByFamAndImAndOtAndDr(dataFilePatient.getFam(),
                        dataFilePatient.getIm(), dataFilePatient.getOt(),
                        dataFilePatient.getDr(), dataFilePatient.getEnp());
                if (people != null) {
                    peoples.add(people);
                    dnGetService.save(new DNGet(dataFile, dataFilePatient, people));
                } else {
                    people = peopleRepo.findPeopleByEnp(dataFilePatient.getEnp());
                    if (people != null) {
                        peoples.add(update(dataFilePatient));
                        dnGetService.save(new DNGet(dataFile, dataFilePatient, people));
                    }
                    else {
                        People newPeople = new People(dataFilePatient);
                        peoples.add(save(newPeople));
                        dnGetService.save(new DNGet(dataFile, dataFilePatient, newPeople));
                    }
                }
            } else peoples.add(new People(dataFilePatient));
        }
        return peoples;
    }

    private DataFile searchFromSRZ(DataFile DataFile) throws IOException, InterruptedException {
        return dbfSearchService.getDataFromDBF(DataFile);
    }

    public People save(People people){
        return peopleRepo.save(people);
    }

    @Transactional
    public People update(DataFilePatient dataFilePatient){
        People peopleUdio = peopleRepo.findPeopleByFamAndImAndOtAndDr(dataFilePatient.getFam(), dataFilePatient.getIm(),
                dataFilePatient.getOt(), dataFilePatient.getDr(), dataFilePatient.getEnp());
        peopleUdio.setEnp(dataFilePatient.getEnp());
        peopleUdio.setSex(dataFilePatient.getSex());
        peopleUdio.setDr(dataFilePatient.getDr());
        peopleUdio.setOt(dataFilePatient.getOt());
        peopleUdio.setFam(dataFilePatient.getFam());
        peopleUdio.setIm(dataFilePatient.getIm());
        peopleUdio.setIdsrz(dataFilePatient.getIdsrz());
        peopleUdio.setDate_edit(Date.from(Instant.now()));
        return peopleRepo.save(peopleUdio);
    }

    public void treatment(DataFile dataFile, Long id) throws IOException, InterruptedException {
        dataFileService.save(dataFile);
            dataUdioRespIdentyService.updateProcessEnd(
                dataUdioRespService.getListDataUdioFromPeoples(
                    searchFromUdio(
                        searchFromSRZ(dataFile))), id);
    }
}
