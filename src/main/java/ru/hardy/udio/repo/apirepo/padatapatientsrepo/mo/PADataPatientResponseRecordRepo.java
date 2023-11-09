package ru.hardy.udio.repo.apirepo.padatapatientsrepo.mo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientResponseRecord;

@Repository
public interface PADataPatientResponseRecordRepo extends JpaRepository<PADataPatientResponseRecord, Long> {
}
