package ru.hardy.udio.service.apiservice.choosingmoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMO;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.choosingmorepo.ChoosingMORepo;

import java.time.Instant;
import java.util.Date;

@Service
public class ChoosingMOService {

    @Autowired
    private ChoosingMORepo choosingMORepo;

    public void add(People people, ChoosingMORequestRecord choosingMORequestRecord, int codeMO){
        ChoosingMO choosingMOFromDB = choosingMORepo.findChoosingMOByPeople(people);
        if (choosingMOFromDB != null){
            choosingMOFromDB.setCodeMO(codeMO);
            choosingMOFromDB.setDate_edit(Date.from(Instant.now()));
            choosingMOFromDB.setRequestRecord(choosingMORequestRecord);
            choosingMORepo.save(choosingMOFromDB);
        } else {
            ChoosingMO choosingMO = new ChoosingMO();
            choosingMO.setPeople(people);
            choosingMO.setDate_beg(Date.from(Instant.now()));
            choosingMO.setDate_edit(Date.from(Instant.now()));
            choosingMO.setCodeMO(codeMO);
            choosingMO.setRequestRecord(choosingMORequestRecord);
            choosingMORepo.save(choosingMO);
        }
    }

    public boolean checkPatient(People people, int codeMO) {
        return choosingMORepo.findChoosingMOByPeopleAndCodeMO(people, codeMO) != null;
    }
}
