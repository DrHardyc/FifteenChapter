package ru.hardy.udio.service.apiservice.individualinformingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualinforming.IndividualInformingRequestRepo;
import ru.hardy.udio.service.apiservice.individualhistoryinformingresponseservice.IndividualHistoryInformingService;

import java.sql.Date;
import java.time.Instant;

@Service
public class IndividualInformingRequestService {

    @Autowired
    private IndividualInformingRequestRepo individualInformingRequestRepo;

    public void add(IndividualInformingRequest individualInformingRequest, int codeMO) {
        individualInformingRequest.getPatients().forEach(patient -> {
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
            patient.setRequest(individualInformingRequest);
        });
        individualInformingRequest.setDate_beg(Date.from(Instant.now()));
        individualInformingRequest.setDate_edit(Date.from(Instant.now()));
        individualInformingRequest.setCodeMO(codeMO);
        individualInformingRequestRepo.save(individualInformingRequest);
    }

    public void update(IndividualInformingRequest individualInformingRequest){
        individualInformingRequest.getPatients().forEach(patient -> {
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
            patient.setRequest(individualInformingRequest);
        });
        individualInformingRequestRepo.save(individualInformingRequest);
    }

}
