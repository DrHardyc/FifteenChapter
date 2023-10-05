package ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;

@Repository
public interface IndividualHistoryInformingResponseRepo extends JpaRepository<IndividualHistoryInformingResponse, Long> {
    IndividualHistoryInformingResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
