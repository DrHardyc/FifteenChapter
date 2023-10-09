package ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;

@Repository
public interface IndividualHistoryOnkoCaseRequestRepo extends JpaRepository<IndividualHistoryOnkoCaseRequest, Long> {
}
