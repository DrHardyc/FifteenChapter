package ru.hardy.udio.repo.apirepo.padatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsResponseRecord;

@Repository
public interface PADataPatientsResponseRecordRepo extends JpaRepository<PADataPatientsResponseRecord, Long> {
}
