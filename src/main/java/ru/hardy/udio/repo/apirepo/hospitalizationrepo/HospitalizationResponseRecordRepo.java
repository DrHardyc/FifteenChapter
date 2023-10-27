package ru.hardy.udio.repo.apirepo.hospitalizationrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationResponseRecord;

@Repository
public interface HospitalizationResponseRecordRepo extends JpaRepository<HospitalizationResponseRecord, Long> {
}
