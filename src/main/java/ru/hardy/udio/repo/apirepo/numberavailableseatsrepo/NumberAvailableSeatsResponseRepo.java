package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponse;

import java.util.List;

@Repository
public interface NumberAvailableSeatsResponseRepo extends JpaRepository<NumberAvailableSeatsResponse, Long> {
    List<NumberAvailableSeatsResponse> findAllByReqIDAndCodeMO(String reqID, int codeMO);

}
