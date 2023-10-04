package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.PADataPatient;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientsRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class PADataPatientsService {

    @Autowired
    private PADataPatientsRepo paDataPatientsRepo;


    public void add(People people, PADataPatientsRequestRecord PADataPatientsRequestRecord, int codeMO){
        PADataPatient PADataPatient = new PADataPatient();
        PADataPatient.setPeople(people);
        PADataPatient.setDateBeg(Date.from(Instant.now()));
        PADataPatient.setDateEdit(Date.from(Instant.now()));
        PADataPatient.setCodeMO(codeMO);
        PADataPatient.setRequestRecord(PADataPatientsRequestRecord);
        paDataPatientsRepo.save(PADataPatient);
    }

    public void update(PADataPatient paDataPatient){
        paDataPatientsRepo.save(paDataPatient);
    }

    public boolean checkPatient(People people, int codeMO, String mainDiag, int codeTypePreventiveActions, Date dateInsuranceCase) {
        return paDataPatientsRepo.findDODataPatientsByPeopleAndCodeMOAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActionsAndRequestRecord_DateInsuranceCase(
                people, codeMO, mainDiag, codeTypePreventiveActions, dateInsuranceCase) != null;
    }

    public PADataPatient searchPatient(People people, int codeMO, String mainDiag, int codeTypePreventiveActions) {
        return paDataPatientsRepo.findDODataPatientsByPeopleAndCodeMOAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActions(
                people, codeMO, mainDiag, codeTypePreventiveActions);
    }
}
