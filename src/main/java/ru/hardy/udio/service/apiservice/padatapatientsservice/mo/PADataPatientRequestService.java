package ru.hardy.udio.service.apiservice.padatapatientsservice.mo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequest;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.mo.PADataPatientRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.time.Instant;
import java.util.Date;

@Service
public class PADataPatientRequestService implements APIRequestServiceInterface {

    @Autowired
    private PADataPatientRequestRepo paDataPatientRequestRepo;

    @Override
    public void add(APIRequest apiRequest) {
        PADataPatientRequest paDataPatientRequest = (PADataPatientRequest) apiRequest;
        paDataPatientRequest.setDateBeg(Date.from(Instant.now()));
        paDataPatientRequest.setDateEdit(Date.from(Instant.now()));
        paDataPatientRequest.getPatients().forEach(patient -> {
            patient.setRequest(paDataPatientRequest);
            patient.setDateBeg(Date.from(Instant.now()));
            patient.setDateEdit(Date.from(Instant.now()));
        });
        paDataPatientRequestRepo.save(paDataPatientRequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((PADataPatientRequest) apiRequest).getPatients() != null;
    }
}
