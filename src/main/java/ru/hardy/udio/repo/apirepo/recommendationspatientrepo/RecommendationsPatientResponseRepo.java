package ru.hardy.udio.repo.apirepo.recommendationspatientrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientResponse;

@Repository
public interface RecommendationsPatientResponseRepo extends JpaRepository<RecommendationsPatientResponse, Long> {
    APIResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
