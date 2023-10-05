package ru.hardy.udio.repo.apirepo.padatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientResponse;

@Repository
public interface PADataPatientResponseRepo extends JpaRepository<PADataPatientResponse, Long> {
    PADataPatientResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
