package ru.hardy.udio.repo.apirepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequest;

@Repository
public interface PatientOnkoCaseRequestRepo extends JpaRepository<PatientOnkoCaseRequest, Long> {
}
