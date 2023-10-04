package ru.hardy.udio.service.apiservice.choosingmoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.repo.apirepo.choosingmorepo.ChoosingMORequestRecordRepo;

import java.util.List;

@Service
public class ChoosingMORequestRecordService {

    @Autowired
    private ChoosingMORequestRecordRepo choosingMORequestRecordRepo;

    public void add(ChoosingMORequestRecord choosingMORequestRecord){
        choosingMORequestRecordRepo.save(choosingMORequestRecord);
    }

    public void addAll(List<ChoosingMORequestRecord> choosingMORequestRecords){
        choosingMORequestRecordRepo.saveAll(choosingMORequestRecords);
    }
}
