package ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponseRecord;

@Repository
public interface IndividualHistoryInformingResponseRecordRepo extends JpaRepository<IndividualHistoryInformingResponseRecord, Long> {
}
