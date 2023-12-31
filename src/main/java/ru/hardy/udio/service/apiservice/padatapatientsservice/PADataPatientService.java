package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.PADataPatient;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class PADataPatientService {

    @Autowired
    private PADataPatientRepo paDataPatientRepo;


    public void add(People people, PADataPatientRequestRecord paDataPatientRequestRecord){
        PADataPatient paDataPatient = new PADataPatient();
        paDataPatient.setPeople(people);
        paDataPatient.setDateBeg(Date.from(Instant.now()));
        paDataPatient.setDateEdit(Date.from(Instant.now()));
        paDataPatient.setRequestRecord(paDataPatientRequestRecord);
        paDataPatientRepo.save(paDataPatient);
    }

    public void update(PADataPatient paDataPatient){
        paDataPatientRepo.save(paDataPatient);
    }

    public PADataPatient searchPatient(People people, String mainDiag, int codeType, Date dateInsurance) {
        return paDataPatientRepo.findByPeopleAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActionsAndRequestRecord_DateInsuranceCase(
                people, mainDiag, codeType, dateInsurance);
    }

    public PADataPatient searchPatient(People people){
        return paDataPatientRepo.findByPeople(people);
    }
}
