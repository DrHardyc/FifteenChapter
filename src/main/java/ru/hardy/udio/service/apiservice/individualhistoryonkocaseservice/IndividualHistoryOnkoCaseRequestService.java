package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo.IndividualHistoryOnkoCaseRequestRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class IndividualHistoryOnkoCaseRequestService {

    @Autowired
    private IndividualHistoryOnkoCaseRequestRepo individualHistoryOnkoCaseRequestRepo;

    public void add(IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest){
        individualHistoryOnkoCaseRequest.getPatients().forEach(patient -> {
                    patient.setRequest(individualHistoryOnkoCaseRequest);
                    patient.setDateBeg(Date.from(Instant.now()));
                    patient.setDateEdit(Date.from(Instant.now()));
                });
        individualHistoryOnkoCaseRequestRepo.save(individualHistoryOnkoCaseRequest);
    }
}
