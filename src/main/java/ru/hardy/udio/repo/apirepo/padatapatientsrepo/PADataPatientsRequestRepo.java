package ru.hardy.udio.repo.apirepo.padatapatientsrepo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsRequest;

@Repository
public interface PADataPatientsRequestRepo extends JpaRepository<PADataPatientsRequest, Long> {
}
