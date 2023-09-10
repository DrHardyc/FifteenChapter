package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.ChoosingMOResponseRecord;
import ru.hardy.udio.repo.apirepo.ChoosingMOResponseRecordRepo;

import java.util.List;

@Service
public class ChoosingMOResponseRecordService {

    @Autowired
    private ChoosingMOResponseRecordRepo choosingMOResponseRecordRepo;

    public void addAll(List<ChoosingMOResponseRecord> choosingMOResponseRecords){
        choosingMOResponseRecordRepo.saveAll(choosingMOResponseRecords);

    }
}
