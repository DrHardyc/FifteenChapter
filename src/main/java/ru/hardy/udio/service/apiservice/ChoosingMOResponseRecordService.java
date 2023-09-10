package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.ChoosingMO;
import ru.hardy.udio.domain.api.ChoosingMORequestRecord;
import ru.hardy.udio.domain.api.ChoosingMOResponseRecord;
import ru.hardy.udio.repo.apirepo.ChoosingMOResponseRecordRepo;
import ru.hardy.udio.service.PeopleService;

import java.util.List;

@Service
public class ChoosingMOResponseRecordService {

    @Autowired
    private ChoosingMOResponseRecordRepo choosingMOResponseRecordRepo;


    @Autowired
    private ChoosingMOService choosingMOService;

    public void addAll(List<ChoosingMOResponseRecord> choosingMOResponseRecords){
        choosingMOResponseRecords.forEach(patient -> {
            if (patient.getRespCode() != 502){
                choosingMOResponseRecordRepo.save(patient);
            }
        });
    }
}
