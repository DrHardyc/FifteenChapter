package ru.hardy.udio.service.apiservice.hospitalizationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationRequest;
import ru.hardy.udio.repo.apirepo.hospitalizationrepo.HospitalizationRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.time.Instant;
import java.util.Date;

@Service
public class HospitalizationRequestService implements APIRequestServiceInterface {
    @Autowired
    private HospitalizationRequestRepo hospitalizationRequestRepo;

    @Override
    public void add(APIRequest apiRequest) {
        HospitalizationRequest hospitalizationRequest = (HospitalizationRequest) apiRequest;
        hospitalizationRequest.getDepartments().forEach(department -> {
            department.setRequest(hospitalizationRequest);
            department.setDateBeg(Date.from(Instant.now()));
            department.setDateEdit(Date.from(Instant.now()));
            department.getPatients().forEach(patient -> {
                patient.setRequestRecord(department);
                patient.setDateBeg(Date.from(Instant.now()));
                patient.setDateEdit(Date.from(Instant.now()));
            });
        });
        hospitalizationRequestRepo.save((HospitalizationRequest) apiRequest);
    }

    @Override
    public boolean checkChild(APIRequest request) {
        return false;
    }

}
