package ru.hardy.udio.repo.apirepo.individualinforming;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualinforming.IndividualInforming;
import ru.hardy.udio.domain.struct.People;

@Repository
public interface IndividualInformingRepo extends JpaRepository<IndividualInforming, Long> {
    IndividualInforming findDODataPatientsByPeopleAndCodeMOAndRequestRecord_MainDiagnosis(People people, int codeMO, String mainDiagnosis);
}
