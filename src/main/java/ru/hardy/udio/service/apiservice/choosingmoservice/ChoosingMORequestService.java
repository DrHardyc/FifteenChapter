package ru.hardy.udio.service.apiservice.choosingmoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;
import ru.hardy.udio.repo.apirepo.choosingmorepo.ChoosingMORequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.time.Instant;
import java.util.Date;

@Service
public class ChoosingMORequestService implements APIRequestServiceInterface {
    @Autowired
    private ChoosingMORequestRepo choosingMORequestRepo;

    public void add(APIRequest apiRequest){
        ChoosingMORequest choosingMORequest = (ChoosingMORequest) apiRequest;
        choosingMORequest.setDateBeg(Date.from(Instant.now()));
        choosingMORequest.setDateEdit(Date.from(Instant.now()));
        choosingMORequest.getPatients().forEach(patientRequest -> {
            patientRequest.setRequest(choosingMORequest);
            patientRequest.setDateBeg(Date.from(Instant.now()));
            patientRequest.setDateEdit(Date.from(Instant.now()));
        });
        choosingMORequestRepo.save(choosingMORequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((ChoosingMORequest) apiRequest).getPatients() != null;
    }
}
