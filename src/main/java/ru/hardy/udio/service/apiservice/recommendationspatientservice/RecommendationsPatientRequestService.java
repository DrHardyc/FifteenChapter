package ru.hardy.udio.service.apiservice.recommendationspatientservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientRequest;
import ru.hardy.udio.repo.apirepo.recommendationspatientrepo.RecommendationsPatientRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.time.Instant;
import java.util.Date;

@Service
public class RecommendationsPatientRequestService implements APIRequestServiceInterface {


    @Autowired
    private RecommendationsPatientRequestRepo recommendationsPatientRequestRepo;

    @Override
    public void add(APIRequest apiRequest) {
        RecommendationsPatientRequest recommendationsPatientRequest = (RecommendationsPatientRequest) apiRequest;
        recommendationsPatientRequest.getPatients().forEach(patient -> {
            patient.setRequest(recommendationsPatientRequest);
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
        });
        recommendationsPatientRequestRepo.save((RecommendationsPatientRequest) apiRequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((RecommendationsPatientRequest) apiRequest).getPatients() != null;
    }
}
