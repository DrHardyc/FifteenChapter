package ru.hardy.udio.repo.apirepo.padatapatientsrepo.mo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatient;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;
import java.util.List;

@Repository
public interface PADataPatientRepo extends JpaRepository<PADataPatient, Long> {
    PADataPatient findByPeopleAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActionsAndRequestRecord_StatusTypePreventiveActionsAndRequestRecord_DateInsuranceCase(
            People people, String requestRecord_mainDiagnosis, int requestRecord_codeTypePreventiveActions, int requestRecord_statusTypePreventiveActions, Date requestRecord_dateInsuranceCase);

    PADataPatient findByPeople(People people);

    List<PADataPatient> findAllByRequestRecord_CodeTypePreventiveActionsAndRequestRecord_StatusTypePreventiveActions(int type, int status);

    PADataPatient findByPeopleAndRequestRecord_CodeTypePreventiveActionsAndRequestRecord_StatusTypePreventiveActions(People people, int par1, int par2);
}