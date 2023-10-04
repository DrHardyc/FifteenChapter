package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsRequest;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientsRequestRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class PADataPatientsRequestService {

    @Autowired
    private PADataPatientsRequestRepo paDataPatientsRequestRepo;

    public void add(PADataPatientsRequest doDataPatientsRequest) {
        doDataPatientsRequest.getPatients().forEach(patient -> {
            patient.setRequest(doDataPatientsRequest);
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
        });
        paDataPatientsRequestRepo.save(doDataPatientsRequest);
    }
}
