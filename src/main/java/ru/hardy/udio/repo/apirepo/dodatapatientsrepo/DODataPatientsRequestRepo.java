package ru.hardy.udio.repo.apirepo.dodatapatientsrepo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsRequest;

@Repository
public interface DODataPatientsRequestRepo extends JpaRepository<DODataPatientsRequest, Long> {
}
