package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequest;
import ru.hardy.udio.repo.apirepo.volumemedicalcarerepo.VolumeMedicalCareRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.sql.Date;
import java.time.Instant;

@Service
public class VolumeMedicalCareRequestService implements APIRequestServiceInterface {

    @Autowired
    private VolumeMedicalCareRequestRepo volumeMedicalCareRequestRepo;


    @Override
    public void add(APIRequest apiRequest) {
        VolumeMedicalCareRequest volumeMedicalCareRequest = (VolumeMedicalCareRequest) apiRequest;
        volumeMedicalCareRequest.setDateBeg(Date.from(Instant.now()));
        volumeMedicalCareRequest.setDateEdit(Date.from(Instant.now()));
        volumeMedicalCareRequest.getDepartments().forEach(department -> {
            department.setRequest(volumeMedicalCareRequest);
            department.setDateBeg(Date.from(Instant.now()));
            department.setDateEdit(Date.from(Instant.now()));
            department.getDiagnoses().forEach(diagnosis -> {
                diagnosis.setRequestRecord(department);
                diagnosis.setDateBeg(Date.from(Instant.now()));
                diagnosis.setDateEdit(Date.from(Instant.now()));
            });
        });
        volumeMedicalCareRequestRepo.save(volumeMedicalCareRequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((VolumeMedicalCareRequest) apiRequest).getDepartments() != null;
    }
}
