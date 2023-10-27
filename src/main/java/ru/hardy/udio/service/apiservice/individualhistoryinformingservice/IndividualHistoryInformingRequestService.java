package ru.hardy.udio.service.apiservice.individualhistoryinformingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo.IndividualHistoryInformingRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.time.Instant;
import java.util.Date;

@Service
public class IndividualHistoryInformingRequestService implements APIRequestServiceInterface {

    @Autowired
    private IndividualHistoryInformingRequestRepo individualHistoryInformingRequestRepo;

    public void add(APIRequest apiRequest) {
        IndividualHistoryInformingRequest individualHistoryInformingRequest = (IndividualHistoryInformingRequest) apiRequest;
        individualHistoryInformingRequest.setDateBeg(Date.from(Instant.now()));
        individualHistoryInformingRequest.setDateEdit(Date.from(Instant.now()));
        individualHistoryInformingRequest.getPatients().forEach(patient -> {
            patient.setRequest(individualHistoryInformingRequest);
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
        });
        individualHistoryInformingRequestRepo.save(individualHistoryInformingRequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((IndividualHistoryInformingRequest) apiRequest).getPatients() != null;
    }
}
