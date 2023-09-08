package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequest;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequestRecord;
import ru.hardy.udio.repo.apirepo.PatientOnkoCaseRequestRecordRepo;

@Service
public class PatientOnkoCaseRequestRecordService {

    @Autowired
    private PatientOnkoCaseRequestRecordRepo patientOnkoCaseRequestRecordRepo;

    public void add(PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord){
        patientOnkoCaseRequestRecordRepo.save(patientOnkoCaseRequestRecord);
    }

    public void addAll(PatientOnkoCaseRequest patientOnkoCaseRequest){
        for (PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord : patientOnkoCaseRequest.getPatients()){
            patientOnkoCaseRequestRecord.setRequest(patientOnkoCaseRequest);
            patientOnkoCaseRequestRecordRepo.save(patientOnkoCaseRequestRecord);
        }
    }
}
