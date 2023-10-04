package ru.hardy.udio.service.apiservice.padatapatientsservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsResponseRecord;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientsResponseRecordRepo;

import java.util.List;

@Service
public class PADataPatientsResponseRecordService {

    @Autowired
    private PADataPatientsResponseRecordRepo doDataPatientsResponseRecordRepo;

    public void addAll(List<PADataPatientsResponseRecord> doDataPatientsResponseRecords) {
        doDataPatientsResponseRecordRepo.saveAll(doDataPatientsResponseRecords);
    }
}
