package ru.hardy.udio.service.apiservice.padatapatientsservice.mo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatient;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.mo.PADataPatientRepo;

import java.time.Instant;
import java.util.Date;
import java.util.List;

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

    public PADataPatient searchPatient(People people, String mainDiag, int codeType, int status,  Date dateInsurance) {
        return paDataPatientRepo.
                findByPeopleAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActionsAndRequestRecord_StatusTypePreventiveActionsAndRequestRecord_DateInsuranceCase(
                people, mainDiag, codeType, status, dateInsurance);
    }

    public PADataPatient searchPatient(People people){
        return paDataPatientRepo.findByPeople(people);
    }



    public List<PADataPatient> getAllByTypeAndStatus(int type, int status){
        return paDataPatientRepo.findAllByRequestRecord_CodeTypePreventiveActionsAndRequestRecord_StatusTypePreventiveActions(type, status);
    }
}
