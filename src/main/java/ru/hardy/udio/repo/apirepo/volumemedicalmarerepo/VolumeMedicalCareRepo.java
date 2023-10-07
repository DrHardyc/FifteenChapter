package ru.hardy.udio.repo.apirepo.volumemedicalmarerepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCare;

@Repository
public interface VolumeMedicalCareRepo extends JpaRepository<VolumeMedicalCare, Long> {
    VolumeMedicalCare findByCodeMOAndRequestRecord_CodeDep(int codeMO, int codeDep);
}
