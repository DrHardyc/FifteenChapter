package ru.hardy.udio.service.apiservice.individualinformingservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualinforming.IndividualInforming;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualinformingrepo.IndividualInformingRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class IndividualInformingService {

    @Autowired
    private IndividualInformingRepo individualInformingRepo;

    public void add(People people, IndividualInformingRequestRecord individualInformingRequestRecord) {
        IndividualInforming individualInforming = new IndividualInforming();
        individualInforming.setPeople(people);
        individualInforming.setDateBeg(Date.from(Instant.now()));
        individualInforming.setDateEdit(Date.from(Instant.now()));
        individualInforming.setCodeMO(individualInformingRequestRecord.getRequest().getCodeMO());
        individualInforming.setRequestRecord(individualInformingRequestRecord);
        individualInformingRepo.save(individualInforming);
    }

    public boolean checkPatient(People people, int codeMO, String mainDiagnosis) {
        return individualInformingRepo
                .findDODataPatientsByPeopleAndCodeMOAndRequestRecord_MainDiagnosis(people, codeMO, mainDiagnosis) != null;
    }
}
