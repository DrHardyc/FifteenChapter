package ru.hardy.udio.service.apiservice.individualhistoryinformingresponseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo.IndividualHistoryInformingRequestRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class IndividualHistoryInformingRequestService {

    @Autowired
    private IndividualHistoryInformingRequestRepo individualHistoryInformingRequestRepo;

    public void add(IndividualHistoryInformingRequest individualHistoryInformingRequest) {
        individualHistoryInformingRequest.getPatients().forEach(patient -> {
            patient.setRequest(individualHistoryInformingRequest);
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
        });
        individualHistoryInformingRequestRepo.save(individualHistoryInformingRequest);
    }
}
