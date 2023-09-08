package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequest;
import ru.hardy.udio.repo.apirepo.PatientOnkoCaseRequestRepo;

@Service
public class PatientOnkoCaseRequestService {

    @Autowired
    private PatientOnkoCaseRequestRepo patientOnkoCaseRequestRepo;

    public void add(PatientOnkoCaseRequest patientOnkoCaseRequest){
        patientOnkoCaseRequestRepo.save(patientOnkoCaseRequest);
    }
}
