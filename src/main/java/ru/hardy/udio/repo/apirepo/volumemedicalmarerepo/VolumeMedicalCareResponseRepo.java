package ru.hardy.udio.repo.apirepo.volumemedicalmarerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponse;

@Repository
public interface VolumeMedicalCareResponseRepo extends JpaRepository<VolumeMedicalCareResponse, Long> {
    VolumeMedicalCareResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
