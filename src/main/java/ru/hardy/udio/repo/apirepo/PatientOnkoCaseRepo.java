package ru.hardy.udio.repo.apirepo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hardy.udio.domain.api.PatientOnkoCase;

public interface PatientOnkoCaseRepo extends JpaRepository<PatientOnkoCase, Long> {
}
