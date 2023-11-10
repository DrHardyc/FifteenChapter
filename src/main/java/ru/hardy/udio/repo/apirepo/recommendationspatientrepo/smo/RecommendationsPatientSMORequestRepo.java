package ru.hardy.udio.repo.apirepo.recommendationspatientrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMORequest;

@Repository
public interface RecommendationsPatientSMORequestRepo extends JpaRepository<RecommendationsPatientSMORequest, Long> {
}
