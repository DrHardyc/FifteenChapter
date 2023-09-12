package ru.hardy.udio.service.apiservice.choosingmoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponseRecord;
import ru.hardy.udio.repo.apirepo.choosingmorepo.ChoosingMOResponseRecordRepo;

import java.util.List;

@Service
public class ChoosingMOResponseRecordService {

    @Autowired
    private ChoosingMOResponseRecordRepo choosingMOResponseRecordRepo;


    @Autowired
    private ChoosingMOService choosingMOService;

    public void addAll(List<ChoosingMOResponseRecord> choosingMOResponseRecords){
        choosingMOResponseRecordRepo.saveAll(choosingMOResponseRecords);
    }
}
