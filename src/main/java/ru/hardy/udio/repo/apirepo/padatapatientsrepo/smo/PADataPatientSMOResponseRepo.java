package ru.hardy.udio.repo.apirepo.padatapatientsrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.padatapatients.smo.PADataPatientSMOResponse;

@Repository
public interface PADataPatientSMOResponseRepo extends JpaRepository<PADataPatientSMOResponse, Long> {
    APIResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
