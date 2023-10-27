package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCare;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;
import ru.hardy.udio.repo.apirepo.volumemedicalcarerepo.VolumeMedicalCareRepo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class VolumeMedicalCareService {

    @Autowired
    private VolumeMedicalCareRepo volumeMedicalCareRepo;


    public void add(VolumeMedicalCareRequestRecord departmentRequest, int codeMO) {
        VolumeMedicalCare volumeMedicalCare = new VolumeMedicalCare();
        volumeMedicalCare.setCodeMO(codeMO);
        volumeMedicalCare.setDate_beg(Date.from(Instant.now()));
        volumeMedicalCare.setDate_edit(Date.from(Instant.now()));
        volumeMedicalCare.setRequestRecord(departmentRequest);
        volumeMedicalCareRepo.save(volumeMedicalCare);
    }

    public void update(VolumeMedicalCare volumeMedicalCare, VolumeMedicalCareRequestRecord departmentRequest) {
        volumeMedicalCare.setDate_edit(Date.from(Instant.now()));
        volumeMedicalCare.setRequestRecord(departmentRequest);
        volumeMedicalCareRepo.save(volumeMedicalCare);
    }

    public VolumeMedicalCare getByAllCodeDepAndCodeMOBef9(int codeDep, int codeMO, Instant instant) {
        return volumeMedicalCareRepo.findByAllCodeDepAndCodeMOBef9(codeDep, codeMO, instant);
    }

    public VolumeMedicalCare getAllByCodeDepAndCodeMOAft9(int codeDep, int codeMO, Instant instant) {
        return volumeMedicalCareRepo.findAllByCodeDepAndCodeMOAft9(codeDep, codeMO, instant);
    }
}
