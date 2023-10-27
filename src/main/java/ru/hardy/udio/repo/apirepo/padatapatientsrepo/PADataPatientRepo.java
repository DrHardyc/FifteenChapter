package ru.hardy.udio.repo.apirepo.padatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.PADataPatient;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Repository
public interface PADataPatientRepo extends JpaRepository<PADataPatient, Long> {
    PADataPatient findByPeopleAndRequestRecord_MainDiagnosisAndRequestRecord_CodeTypePreventiveActionsAndRequestRecord_DateInsuranceCase(
            People people, String mainDiag, int codeType, Date dateInsurance);

    PADataPatient findByPeople(People people);
}
