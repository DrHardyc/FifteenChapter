package ru.hardy.udio.service.apiservice.dodatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatients;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsRequest;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.dodatapatientsrepo.DODataPatientsRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class DODataPatientsService {

    @Autowired
    private DODataPatientsRepo doDataPatientsRepo;


    public void add(People people, DODataPatientsRequest doDataPatientsRequest, int codeMO){
        DODataPatients doDataPatients = new DODataPatients();
        doDataPatients.setPeople(people);
        doDataPatients.setDateBeg(Date.from(Instant.now()));
        doDataPatients.setDateEdit(Date.from(Instant.now()));
        doDataPatients.setCodeMO(codeMO);
        doDataPatients.setRequest(doDataPatientsRequest);
        doDataPatientsRepo.save(doDataPatients);
    }

    public boolean checkPatient(People people, int codeMO) {
        return doDataPatientsRepo.findDODataPatientsByPeopleAndCodeMO(people, codeMO) != null;
    }
}
