package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequest;
import ru.hardy.udio.repo.apirepo.volumemedicalmarerepo.VolumeMedicalCareRequestRepo;

import java.sql.Date;
import java.time.Instant;

@Service
public class VolumeMedicalCareRequestService {

    @Autowired
    private VolumeMedicalCareRequestRepo volumeMedicalCareRequestRepo;


    public void add(VolumeMedicalCareRequest volumeMedicalCareRequest) {
        volumeMedicalCareRequest.getDepartments().forEach(department -> {
            department.setRequest(volumeMedicalCareRequest);
            department.setDate_beg(Date.from(Instant.now()));
            department.setDate_edit(Date.from(Instant.now()));
        });
        volumeMedicalCareRequestRepo.save(volumeMedicalCareRequest);
    }
}
