package ru.hardy.udio.service.apiservice.individualinformingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.repo.apirepo.individualinformingrepo.IndividualInformingRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.sql.Date;
import java.time.Instant;

@Service
public class IndividualInformingRequestService implements APIRequestServiceInterface {

    @Autowired
    private IndividualInformingRequestRepo individualInformingRequestRepo;

    @Override
    public void add(APIRequest apiRequest) {
        IndividualInformingRequest individualInformingRequest = (IndividualInformingRequest) apiRequest;
        individualInformingRequest.setDateBeg(java.util.Date.from(Instant.now()));
        individualInformingRequest.setDateEdit(java.util.Date.from(Instant.now()));
        individualInformingRequest.getPatients().forEach(patient -> {
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
            patient.setRequest(individualInformingRequest);
        });
        apiRequest.setDateBeg(Date.from(Instant.now()));
        apiRequest.setDateEdit(Date.from(Instant.now()));
        individualInformingRequestRepo.save(individualInformingRequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((IndividualInformingRequest) apiRequest).getPatients() != null;
    }

    public void update(APIRequest apiRequest){
        IndividualInformingRequest individualInformingRequest = (IndividualInformingRequest) apiRequest;
        individualInformingRequest.getPatients().forEach(patient -> {
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
            patient.setRequest(individualInformingRequest);
        });
        individualInformingRequestRepo.save(individualInformingRequest);
    }

}
