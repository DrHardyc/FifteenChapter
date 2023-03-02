package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DNGet;
import ru.hardy.udio.repo.DNGetRepo;

@Service
public class DNGetService {

    @Autowired
    private DNGetRepo dnGetRepo;

    public void save(DNGet dnGet){
        dnGetRepo.save(dnGet);
    }
}
