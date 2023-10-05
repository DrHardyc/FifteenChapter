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


    public void add(People people, PADataPatientRequestRecord PADataPatientRequestRecord, int codeMO){
        PADataPatient PADataPatient = new PADataPatient();
        PADataPatient.setPeople(people);
        PADataPatient.setDateBeg(Date.from(Instant.now()));
        PADataPatient.setDateEdit(Date.from(Instant.now()));
        PADataPatient.setCodeMO(codeMO);
        PADataPatient.setRequestRecord(PADataPatientRequestRecord);
        paDataPatientRepo.save(PADataPatient);
    }

    public void update(PADataPatient paDataPatient){
        paDataPatientRepo.save(paDataPatient);
    }

    public boolean checkPatient(People people, int codeMO, String mainDiag, int codeTypePreventiveActions, Date dateInsuranceCase) {
        return paDataPatientRepo.findDODataPatientsByPeopleAndCodeMOAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActionsAndRequestRecord_DateInsuranceCase(
                people, codeMO, mainDiag, codeTypePreventiveActions, dateInsuranceCase) != null;
    }

    public PADataPatient searchPatient(People people, int codeMO, String mainDiag, int codeTypePreventiveActions) {
        return paDataPatientRepo.findDODataPatientsByPeopleAndCodeMOAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActions(
                people, codeMO, mainDiag, codeTypePreventiveActions);
    }
}
