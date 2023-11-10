package ru.hardy.udio.service.apiservice.recommendationspatientservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMORequest;
import ru.hardy.udio.repo.apirepo.recommendationspatientrepo.smo.RecommendationsPatientSMORequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.time.Instant;
import java.util.Date;

@Service
public class RecommendationsPatientSMORequestService implements APIRequestServiceInterface {
    @Autowired
    private RecommendationsPatientSMORequestRepo recommendationsPatientSMORequestRepo;


    @Override
    public void add(APIRequest apiRequest) {
        RecommendationsPatientSMORequest recommendationsPatientSMORequest = (RecommendationsPatientSMORequest) apiRequest;
        recommendationsPatientSMORequest.getPatients().forEach(recommendationsPatientSMORequestRecord -> {
                recommendationsPatientSMORequestRecord.setRequest(recommendationsPatientSMORequest);
                recommendationsPatientSMORequestRecord.setDateBeg(Date.from(Instant.now()));
                recommendationsPatientSMORequestRecord.setDateEdit(Date.from(Instant.now()));
            }
        );
        recommendationsPatientSMORequestRepo.save((RecommendationsPatientSMORequest) apiRequest);
    }

    @Override
    public boolean checkChild(APIRequest request) {
        return true;
    }
}
