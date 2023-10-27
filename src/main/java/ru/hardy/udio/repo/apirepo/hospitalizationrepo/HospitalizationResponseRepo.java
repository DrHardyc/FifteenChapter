package ru.hardy.udio.repo.apirepo.hospitalizationrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationResponse;

@Repository
public interface HospitalizationResponseRepo extends JpaRepository<HospitalizationResponse, Long> {
    HospitalizationResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
