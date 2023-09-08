package ru.hardy.udio.repo.apirepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequestRecord;

@Repository
public interface PatientOnkoCaseRequestRecordRepo extends JpaRepository<PatientOnkoCaseRequestRecord, Long> {
}
