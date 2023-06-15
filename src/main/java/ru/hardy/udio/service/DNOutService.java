package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DNOut;
import ru.hardy.udio.repo.DNOutRepo;

@Service
public class DNOutService {

    @Autowired
    private DNOutRepo dnOutRepo;

    public void add(DNOut dnOut){
        dnOutRepo.save(dnOut);
    }
}
