package ru.hardy.udio.repo.apirepo.individualinformingrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;

@Repository
public interface IndividualInformingResponseRepo extends JpaRepository<IndividualInformingResponse, Long> {

    IndividualInformingResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
