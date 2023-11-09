package ru.hardy.udio.repo.apirepo.padatapatientsrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.smo.PADataPatientSMORequest;

@Repository
public interface PADataPatientSMORequestRepo extends JpaRepository<PADataPatientSMORequest, Long> {
}
