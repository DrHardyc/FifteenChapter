package ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponse;

@Repository
public interface IndividualHistoryOnkoCaseResponseRepo extends JpaRepository<IndividualHistoryOnkoCaseResponse, Long> {
    IndividualHistoryOnkoCaseResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
