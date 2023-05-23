package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.PR168;
import ru.hardy.udio.repo.PR168Repo;

@Service
public class PR168Service {

    @Autowired
    private PR168Repo pr168Repo;

    public PR168 getWithDiagAndKod(String diag, int kod_spec){
        return pr168Repo.findByDiagContainingAndSpec(diag, kod_spec);
    }

}
