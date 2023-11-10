package ru.hardy.udio.repo.apirepo.recommendationspatientrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMOResponse;

@Repository
public interface RecommendationsPatientSMOResponseRepo extends JpaRepository<RecommendationsPatientSMOResponse, Long> {
    APIResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
