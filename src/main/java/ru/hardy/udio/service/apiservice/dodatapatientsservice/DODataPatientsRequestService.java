package ru.hardy.udio.service.apiservice.dodatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsRequest;
import ru.hardy.udio.repo.apirepo.dodatapatientsrepo.DODataPatientsRequestRepo;

@Service
public class DODataPatientsRequestService {

    @Autowired
    private DODataPatientsRequestRepo doDataPatientsRequestRepo;

    public void add(DODataPatientsRequest doDataPatientsRequest){
        doDataPatientsRequestRepo.save(doDataPatientsRequest);
    }
}
