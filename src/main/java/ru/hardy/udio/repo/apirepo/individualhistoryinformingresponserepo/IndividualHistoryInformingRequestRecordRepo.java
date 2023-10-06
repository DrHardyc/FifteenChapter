package ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequestRecord;

@Repository
public interface IndividualHistoryInformingRequestRecordRepo extends JpaRepository<IndividualHistoryInformingRequestRecord, Long> {
}
