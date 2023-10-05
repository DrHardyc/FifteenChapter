package ru.hardy.udio.service.apiservice.padatapatientsservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientResponseRecord;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientResponseRecordRepo;

import java.util.List;

@Service
public class PADataPatientResponseRecordService {

    @Autowired
    private PADataPatientResponseRecordRepo doDataPatientsResponseRecordRepo;

    public void addAll(List<PADataPatientResponseRecord> doDataPatientsResponseRecords) {
        doDataPatientsResponseRecordRepo.saveAll(doDataPatientsResponseRecords);
    }
}
