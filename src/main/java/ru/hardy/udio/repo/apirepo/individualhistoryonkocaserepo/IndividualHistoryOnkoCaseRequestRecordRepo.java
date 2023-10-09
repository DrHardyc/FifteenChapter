package ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequestRecord;

@Repository
public interface IndividualHistoryOnkoCaseRequestRecordRepo extends JpaRepository<IndividualHistoryOnkoCaseRequestRecord, Long> {
}
