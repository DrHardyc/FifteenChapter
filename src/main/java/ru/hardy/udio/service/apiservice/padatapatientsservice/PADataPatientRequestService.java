package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequest;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientRequestRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class PADataPatientRequestService {

    @Autowired
    private PADataPatientRequestRepo paDataPatientRequestRepo;

    public void add(PADataPatientRequest doDataPatientsRequest) {
        doDataPatientsRequest.getPatients().forEach(patient -> {
            patient.setRequest(doDataPatientsRequest);
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
        });
        paDataPatientRequestRepo.save(doDataPatientsRequest);
    }
}
