package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientsRequestRecordRepo;

import java.util.List;

@Service
public class PADataPatientsRequestRecordService {

    @Autowired
    private PADataPatientsRequestRecordRepo paDataPatientsRequestRecordRepo;

    public void add(){}

    public void addAll(List<PADataPatientsRequestRecord> patients) {
        paDataPatientsRequestRecordRepo.saveAll(patients);
    }

    public List<PADataPatientsRequestRecord> getAllByPeople(People people){
        return paDataPatientsRequestRecordRepo.findByPeople(people);
    }

}
