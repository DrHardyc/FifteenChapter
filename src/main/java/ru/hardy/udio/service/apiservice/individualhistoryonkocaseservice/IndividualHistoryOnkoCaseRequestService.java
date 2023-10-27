package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo.IndividualHistoryOnkoCaseRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.time.Instant;
import java.util.Date;

@Service
public class IndividualHistoryOnkoCaseRequestService implements APIRequestServiceInterface {

    @Autowired
    private IndividualHistoryOnkoCaseRequestRepo individualHistoryOnkoCaseRequestRepo;

    public void add(APIRequest apiRequest) {
        IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest = (IndividualHistoryOnkoCaseRequest) apiRequest;
        individualHistoryOnkoCaseRequest.setDateBeg(Date.from(Instant.now()));
        individualHistoryOnkoCaseRequest.setDateEdit(Date.from(Instant.now()));
        individualHistoryOnkoCaseRequest.getPatients().forEach(patient -> {
            patient.setRequest(individualHistoryOnkoCaseRequest);
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
        });
        individualHistoryOnkoCaseRequestRepo.save(individualHistoryOnkoCaseRequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((IndividualHistoryOnkoCaseRequest) apiRequest).getPatients() != null;
    }
}