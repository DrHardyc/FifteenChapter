package ru.hardy.udio.repo.apirepo.recommendationspatientrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatient;
import ru.hardy.udio.domain.struct.People;

@Repository
public interface RecommendationsPatientRepo extends JpaRepository<RecommendationsPatient, Long> {
    RecommendationsPatient findByPeopleAndRequestRecord_Recommendation(People people, String recommended);
}
