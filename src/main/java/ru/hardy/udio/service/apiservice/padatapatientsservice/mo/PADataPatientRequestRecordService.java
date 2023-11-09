package ru.hardy.udio.service.apiservice.padatapatientsservice.mo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.mo.PADataPatientRequestRecordRepo;

import java.util.List;

@Service
public class PADataPatientRequestRecordService {

    @Autowired
    private PADataPatientRequestRecordRepo paDataPatientRequestRecordRepo;

    public void add(){}

    public void addAll(List<PADataPatientRequestRecord> patients) {
        paDataPatientRequestRecordRepo.saveAll(patients);
    }

    public List<PADataPatientRequestRecord> getAllByPeople(People people){
        return paDataPatientRequestRecordRepo.findByPeople(people);
    }

}
