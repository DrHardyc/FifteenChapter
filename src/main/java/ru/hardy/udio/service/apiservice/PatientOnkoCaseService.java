package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.PatientOnkoCase;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequest;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.PatientOnkoCaseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.TokenService;

import java.sql.Date;
import java.time.Instant;

@Service
public class PatientOnkoCaseService {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PatientOnkoCaseRepo patientOnkoCaseRepo;

    @Autowired
    private TokenService tokenService;


//    public void addAll(List<PatientOnkoCase> patientOnkoCases, PatientOnkoCaseRequest patientOnkoCaseRequest, int codeMO){
//        for (PatientOnkoCase patientOnkoCase : patientOnkoCases){
//            People people = search(patientOnkoCase);
//            if (people != null ){
//                patientOnkoCase.setDate_beg(Date.from(Instant.now()));
//                patientOnkoCase.setDate_edit(Date.from(Instant.now()));
//                patientOnkoCase.setRequest(patientOnkoCaseRequest);
//                patientOnkoCase.setPeople(people);
//                patientOnkoCase.setCodeMO(codeMO);
//                patientOnkoCaseRepo.save(patientOnkoCase);
//            };
//        }
//    }

    public People search(PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord){
        return peopleService.search(patientOnkoCaseRequestRecord);
    }

    public void add(PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord,
                    People people, String token) {
        PatientOnkoCase patientOnkoCase = new PatientOnkoCase();
        patientOnkoCase.setDate_beg(Date.from(Instant.now()));
        patientOnkoCase.setDate_edit(Date.from(Instant.now()));
        patientOnkoCase.setDateVisit(patientOnkoCaseRequestRecord.getDateVisit());
        patientOnkoCase.setFirstIdentified(patientOnkoCaseRequestRecord.getFirstIdentified());
        patientOnkoCase.setMainDiagnosis(patientOnkoCaseRequestRecord.getMainDiagnosis());
        patientOnkoCase.setRequest(patientOnkoCaseRequestRecord.getRequest());
        patientOnkoCase.setPeople(people);
        patientOnkoCase.setCodeMO(tokenService.getCodeMOWithToken(token));
        patientOnkoCaseRepo.save(patientOnkoCase);
    }
}
