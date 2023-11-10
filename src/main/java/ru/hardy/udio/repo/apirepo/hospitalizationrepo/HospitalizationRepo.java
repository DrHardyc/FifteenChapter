package ru.hardy.udio.repo.apirepo.hospitalizationrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.hospitalization.mo.Hospitalization;
import ru.hardy.udio.domain.struct.People;

import java.util.List;

@Repository
public interface HospitalizationRepo extends JpaRepository<Hospitalization, Long> {

    Hospitalization findByPeopleAndPatient_MainDiagnosisAndPatient_CodeHospitalization(People people, String mainDiagnosis, int hospitalization);

    List<Hospitalization> findAllByPatient_CodeHospitalization(int codeHospitalization);

    List<Hospitalization> findByPeople(People people);

    List<Hospitalization> findAllByPeople(People people);
}
