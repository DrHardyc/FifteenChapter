package ru.hardy.udio.repo.apirepo.padatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsResponse;

@Repository
public interface PADataPatientsResponseRepo extends JpaRepository<PADataPatientsResponse, Long> {
    PADataPatientsResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
