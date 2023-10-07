package ru.hardy.udio.repo.apirepo.volumemedicalmarerepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponseRecord;

import java.util.List;



@Service
public class VolumeMedicalCareResponseRecordService{

    @Autowired
    private VolumeMedicalCareResponseRecordRepo volumeMedicalCareResponseRecordRepo;


    public void addAll(List<VolumeMedicalCareResponseRecord> volumeMedicalCareResponseRecords) {
        volumeMedicalCareResponseRecordRepo.saveAll(volumeMedicalCareResponseRecords);

    }
}

