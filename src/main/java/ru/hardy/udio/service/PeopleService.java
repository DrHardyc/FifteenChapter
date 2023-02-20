package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.PeopleRepo;
import ru.hardy.udio.service.SRZ.DBFSearchService;

import java.io.IOException;
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

    public List<People> getAll(){
        return peopleRepo.findAll();
    }


    public List<People> searchFromUdio(List<DataFilePatient> dataFilePatientsFromSrz){
        List<People> peoples = new ArrayList<>();
        for (DataFilePatient dataFilePatient : dataFilePatientsFromSrz) {
            if (dataFilePatient.getIdsrz() != null) {
                People people = peopleRepo.findPeopleByFamAndImAndOtAndDr(dataFilePatient.getFam().toUpperCase(),
                        dataFilePatient.getIm().toUpperCase(), dataFilePatient.getOt().toUpperCase(),
                        dataFilePatient.getDr(), dataFilePatient.getEnp());
                if (people != null) {
                    peoples.add(people);
                } else {
                    people = peopleRepo.findPeopleByEnp(dataFilePatient.getEnp());
                    if (people != null)
                        peoples.add(update(dataFilePatient));
                    else
                        peoples.add(save(new People(dataFilePatient)));
                }
            } else peoples.add(new People(dataFilePatient));
        }
        return peoples;
    }

    private List<DataFilePatient> searchFromSRZ(List<DataFilePatient> dataFilePatient) throws IOException, InterruptedException {
        return dbfSearchService.getDataFromDBF(dataFilePatient);
    }

    public People save(People people){
        return peopleRepo.save(people);
    }

    public People update(DataFilePatient dataFilePatient){
        People peopleUdio = peopleRepo.findPeopleByFamAndImAndOtAndDr(dataFilePatient.getFam().toUpperCase(), dataFilePatient.getIm().toUpperCase(),
                dataFilePatient.getOt().toUpperCase(), dataFilePatient.getDr(), dataFilePatient.getEnp());
        peopleUdio.setEnp(dataFilePatient.getEnp());
        peopleUdio.setSex(dataFilePatient.getSex());
        peopleUdio.getNhistory().clear();
        peopleUdio.getNhistory().addAll(dataFilePatient.getNhistory());
        peopleUdio.setDr(dataFilePatient.getDr());
        peopleUdio.setOt(dataFilePatient.getOt());
        peopleUdio.setFam(dataFilePatient.getFam());
        peopleUdio.setIm(dataFilePatient.getIm());
        peopleUdio.setIdsrz(dataFilePatient.getIdsrz());
        return peopleRepo.save(peopleUdio);
    }

    public void treatment(DataFile dataFile, Long id) throws IOException, InterruptedException {
        dataFileService.save(dataFile);
        dataUdioRespIdentyService.updateProcessEnd(
        dataUdioRespService.getListDataUdioFromPeoples(
                searchFromUdio(
                searchFromSRZ(
                dataFile.getDataFilePatient()))), id);
    }
}
