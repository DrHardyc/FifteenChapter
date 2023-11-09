package ru.hardy.udio.service.apiservice.padatapatientsservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.padatapatients.smo.PADataPatientSMORequest;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.smo.PADataPatientSMORequestRepo;

@Service
public class PADataPatientSMORequestService {

    @Autowired
    private PADataPatientSMORequestRepo paDataPatientSMORequestRepo;

    public void add(APIRequest apiRequest){
        paDataPatientSMORequestRepo.save((PADataPatientSMORequest) apiRequest);
    }
}
