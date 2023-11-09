package ru.hardy.udio.repo.apirepo.padatapatientsrepo.mo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientResponse;

@Repository
public interface PADataPatientResponseRepo extends JpaRepository<PADataPatientResponse, Long> {
    PADataPatientResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
