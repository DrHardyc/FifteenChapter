package ru.hardy.udio.repo.apirepo.dodatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatients;
import ru.hardy.udio.domain.struct.People;

@Repository
public interface DODataPatientsRepo extends JpaRepository<DODataPatients, Long> {
    DODataPatients findDODataPatientsByPeopleAndCodeMO(People people, int codeMO);
}
