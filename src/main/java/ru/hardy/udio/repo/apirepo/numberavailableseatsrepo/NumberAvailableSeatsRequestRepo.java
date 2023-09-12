package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;

import java.util.List;

@Repository
public interface NumberAvailableSeatsRequestRepo extends JpaRepository<NumberAvailableSeatsRequest, Long> {
    List<NumberAvailableSeatsRequest> findAllByReqIDAndCodeMO(String reqID, int codeMO);
}
