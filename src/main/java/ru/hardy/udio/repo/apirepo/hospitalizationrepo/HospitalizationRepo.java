package ru.hardy.udio.repo.apirepo.hospitalizationrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.hospitalization.Hospitalization;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Repository
public interface HospitalizationRepo extends JpaRepository<Hospitalization, Long> {

    Hospitalization findByPeopleAndPatient_MainDiagnosisAndPatient_Hospitalization(People people, String mainDiagnosis, int hospitalization);

}
