package ru.hardy.udio.service.apiservice.choosingmoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;
import ru.hardy.udio.repo.apirepo.choosingmorepo.ChoosingMORequestRepo;

@Service
public class ChoosingMORequestService {

    @Autowired
    private ChoosingMORequestRepo choosingMORequestRepo;

    public void add(ChoosingMORequest choosingMORequest){
        choosingMORequestRepo.save(choosingMORequest);
    }
}
