package ru.hardy.udio.repo.apirepo.padatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.PADataPatient;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Repository
public interface PADataPatientRepo extends JpaRepository<PADataPatient, Long> {
    PADataPatient findDODataPatientsByPeopleAndCodeMOAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActionsAndRequestRecord_DateInsuranceCase(
            People people, int codeMO, String mainDiag, int codeTypePreventiveActions, Date insuranceDate);

    PADataPatient findDODataPatientsByPeopleAndCodeMOAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActions(
            People people, int codeMO, String mainDiag, int codeTypePreventiveActions);
}
