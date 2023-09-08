package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.PatientOnkoCaseResponseRecord;
import ru.hardy.udio.repo.apirepo.PatientOnkoCaseResponseRecordRepo;

import java.util.List;

@Service
public class PatientOnkoCaseResponseRecordService {

    @Autowired
    private PatientOnkoCaseResponseRecordRepo patientOnkoCaseResponseRecordRepo;


    public void addAll(List<PatientOnkoCaseResponseRecord> patientOnkoCaseResponseRecords) {
        patientOnkoCaseResponseRecordRepo.saveAll(patientOnkoCaseResponseRecords);
    }
}
