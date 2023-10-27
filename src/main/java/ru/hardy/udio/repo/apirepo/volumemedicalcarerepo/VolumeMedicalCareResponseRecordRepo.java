package ru.hardy.udio.repo.apirepo.volumemedicalcarerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponseRecord;

@Repository
public interface VolumeMedicalCareResponseRecordRepo extends JpaRepository<VolumeMedicalCareResponseRecord, Long> {

}