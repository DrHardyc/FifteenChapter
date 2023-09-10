package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.ChoosingMO;
import ru.hardy.udio.domain.api.ChoosingMORequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.ChoosingMORepo;
import ru.hardy.udio.repo.apirepo.ChoosingMOResponseRepo;
import ru.hardy.udio.service.TokenService;

import java.time.Instant;
import java.util.Date;

@Service
public class ChoosingMOService {

    @Autowired
    private ChoosingMORepo choosingMORepo;

    @Autowired
    private TokenService tokenService;

    public void add(People people, String token){
        ChoosingMO choosingMO = new ChoosingMO();
        choosingMO.setPeople(people);
        choosingMO.setDate_beg(Date.from(Instant.now()));
        choosingMO.setDate_edit(Date.from(Instant.now()));
        choosingMO.setCodeMO(tokenService.getCodeMOWithToken(token));
        choosingMORepo.save(choosingMO);
    }
}
