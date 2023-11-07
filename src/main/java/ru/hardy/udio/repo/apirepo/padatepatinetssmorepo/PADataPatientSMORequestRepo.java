package ru.hardy.udio.repo.apirepo.padatepatinetssmorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatepatinetssmo.PADataPatientSMORequest;

@Repository
public interface PADataPatientSMORequestRepo extends JpaRepository<PADataPatientSMORequest, Long> {
}
