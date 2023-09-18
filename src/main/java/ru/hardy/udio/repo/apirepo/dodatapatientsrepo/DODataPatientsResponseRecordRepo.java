package ru.hardy.udio.repo.apirepo.dodatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsResponseRecord;

@Repository
public interface DODataPatientsResponseRecordRepo extends JpaRepository<DODataPatientsResponseRecord, Long> {
}
