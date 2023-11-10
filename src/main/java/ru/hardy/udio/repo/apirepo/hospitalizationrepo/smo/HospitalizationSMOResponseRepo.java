package ru.hardy.udio.repo.apirepo.hospitalizationrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMOResponse;

@Repository
public interface HospitalizationSMOResponseRepo extends JpaRepository<HospitalizationSMOResponse, Long> {
    APIResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
