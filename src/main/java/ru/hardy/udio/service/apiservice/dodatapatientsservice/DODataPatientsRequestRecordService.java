package ru.hardy.udio.service.apiservice.dodatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsRequestRecord;
import ru.hardy.udio.repo.apirepo.dodatapatientsrepo.DODataPatientsRequestRecordRepo;

import java.util.List;

@Service
public class DODataPatientsRequestRecordService {

    @Autowired
    private DODataPatientsRequestRecordRepo doDataPatientsRequestRecordRepo;

    public void add(){}

    public void addAll(List<DODataPatientsRequestRecord> patients) {
        doDataPatientsRequestRecordRepo.saveAll(patients);
    }
}
