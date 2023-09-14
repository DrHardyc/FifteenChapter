package ru.hardy.udio.repo.apirepo.individualhistoryonkocase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCase;

@Repository
public interface IndividualHistoryOnkoCaseRepo extends JpaRepository<IndividualHistoryOnkoCase, Long> {
}
