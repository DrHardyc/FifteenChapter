package ru.hardy.udio.service.apiservice.dodatapatientsservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsResponseRecord;
import ru.hardy.udio.repo.apirepo.dodatapatientsrepo.DODataPatientsResponseRecordRepo;

import java.util.List;

@Service
public class DODataPatientsResponseRecordService {

    @Autowired
    private DODataPatientsResponseRecordRepo doDataPatientsResponseRecordRepo;

    public void addAll(List<DODataPatientsResponseRecord> doDataPatientsResponseRecords) {
        doDataPatientsResponseRecordRepo.saveAll(doDataPatientsResponseRecords);
    }
}
