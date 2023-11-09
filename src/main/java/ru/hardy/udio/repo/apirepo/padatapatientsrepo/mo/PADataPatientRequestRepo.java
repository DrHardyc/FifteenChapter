package ru.hardy.udio.repo.apirepo.padatapatientsrepo.mo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequest;

@Repository
public interface PADataPatientRequestRepo extends JpaRepository<PADataPatientRequest, Long> {
}
