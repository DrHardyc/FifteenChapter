package ru.hardy.udio.service.apiservice.individualinformingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.repo.apirepo.individualinforming.IndividualInformingRequestRepo;

import java.sql.Date;
import java.time.Instant;

@Service
public class IndividualInformingRequestService {

    @Autowired
    private IndividualInformingRequestRepo individualInformingRequestRepo;

    public void add(IndividualInformingRequest individualInformingRequest) {
        individualInformingRequest.getPatients().forEach(patient -> {
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
            patient.setRequest(individualInformingRequest);
        });
        individualInformingRequestRepo.save(individualInformingRequest);
    }
}
