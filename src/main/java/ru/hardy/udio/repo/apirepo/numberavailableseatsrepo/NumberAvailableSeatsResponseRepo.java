package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsResponse;

@Repository
public interface NumberAvailableSeatsResponseRepo extends JpaRepository<NumberAvailableSeatsResponse, Long> {
    NumberAvailableSeatsResponse findByReqIDAndCodeMO(String reqID, int codeMO);

}
