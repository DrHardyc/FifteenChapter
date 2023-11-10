package ru.hardy.udio.service.apiservice.hospitalizationservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMORequest;
import ru.hardy.udio.repo.apirepo.hospitalizationrepo.smo.HospitalizationSMORequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

@Service

public class HospitalizationSMORequestService implements APIRequestServiceInterface {

    @Autowired
    private HospitalizationSMORequestRepo hospitalizationSMORequestRepo;

    @Override
    public void add(APIRequest apiRequest) {
        hospitalizationSMORequestRepo.save((HospitalizationSMORequest) apiRequest);
    }

    @Override
    public boolean checkChild(APIRequest request) {
        return true;
    }
}
