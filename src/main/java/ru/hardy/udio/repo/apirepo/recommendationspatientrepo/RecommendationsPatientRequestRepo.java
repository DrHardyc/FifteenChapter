package ru.hardy.udio.repo.apirepo.recommendationspatientrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientRequest;

@Repository
public interface RecommendationsPatientRequestRepo extends JpaRepository<RecommendationsPatientRequest, Long> {
}
