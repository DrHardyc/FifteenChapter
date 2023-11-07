package ru.hardy.udio.repo.apirepo.padatepatinetssmorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatepatinetssmo.PADataPatientSMOResponse;

@Repository
public interface PADataPatientSMOResponseRepo extends JpaRepository<PADataPatientSMOResponse, Long> {
}
