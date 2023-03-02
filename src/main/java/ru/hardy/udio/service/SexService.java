package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.Sex;
import ru.hardy.udio.repo.SexRepo;

@Service
public class SexService {

    @Autowired
    private SexRepo sexRepo;

    public Sex getById(Long id){
        return sexRepo.searchById(id);
    }
}
