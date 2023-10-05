package ru.hardy.udio.repo.apirepo.padatapatientsrepo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequest;

@Repository
public interface PADataPatientRequestRepo extends JpaRepository<PADataPatientRequest, Long> {
}
