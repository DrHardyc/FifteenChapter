package ru.hardy.udio.service.apiservice.padatepatinetssmoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.padatepatinetssmo.PADataPatientSMORequest;
import ru.hardy.udio.repo.apirepo.padatepatinetssmorepo.PADataPatientSMORequestRepo;

@Service
public class PADataPatientSMORequestService {

    @Autowired
    private PADataPatientSMORequestRepo paDataPatientSMORequestRepo;

    public void add(APIRequest apiRequest){
        paDataPatientSMORequestRepo.save((PADataPatientSMORequest) apiRequest);
    }
}
