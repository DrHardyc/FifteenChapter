package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlot;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCare;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;
import ru.hardy.udio.repo.apirepo.volumemedicalmarerepo.VolumeMedicalCareRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class VolumeMedicalCareService {

    @Autowired
    private VolumeMedicalCareRepo volumeMedicalCareRepo;


    public void add(VolumeMedicalCareRequestRecord departmentRequest, int codeMO) {
        VolumeMedicalCare volumeMedicalCareFromDB = volumeMedicalCareRepo.findByCodeMOAndRequestRecord_CodeDep(codeMO,
                departmentRequest.getCodeDep());
        if (volumeMedicalCareFromDB != null){
            volumeMedicalCareFromDB.setDate_edit(Date.from(Instant.now()));
            volumeMedicalCareFromDB.setRequestRecord(departmentRequest);
            volumeMedicalCareRepo.save(volumeMedicalCareFromDB);
        } else {
            VolumeMedicalCare volumeMedicalCare = new VolumeMedicalCare();
            volumeMedicalCare.setCodeMO(codeMO);
            volumeMedicalCare.setDate_beg(Date.from(Instant.now()));
            volumeMedicalCare.setDate_edit(Date.from(Instant.now()));
            volumeMedicalCare.setRequestRecord(departmentRequest);
            volumeMedicalCareRepo.save(volumeMedicalCare);
        }
    }
}
