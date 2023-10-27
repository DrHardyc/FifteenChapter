package ru.hardy.udio.repo.apirepo.hospitalizationrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationRequest;

@Repository
public interface HospitalizationRequestRepo extends JpaRepository<HospitalizationRequest, Long> {
    HospitalizationRequest findByReqIDAndCodeMO(String reqID, int codeMO);
}
