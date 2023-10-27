package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponseRecord;
import ru.hardy.udio.repo.apirepo.volumemedicalcarerepo.VolumeMedicalCareResponseRecordRepo;

import java.util.List;



@Service
public class VolumeMedicalCareResponseRecordService{

    @Autowired
    private VolumeMedicalCareResponseRecordRepo volumeMedicalCareResponseRecordRepo;


    public void addAll(List<VolumeMedicalCareResponseRecord> volumeMedicalCareResponseRecords) {
        volumeMedicalCareResponseRecordRepo.saveAll(volumeMedicalCareResponseRecords);

    }
}

