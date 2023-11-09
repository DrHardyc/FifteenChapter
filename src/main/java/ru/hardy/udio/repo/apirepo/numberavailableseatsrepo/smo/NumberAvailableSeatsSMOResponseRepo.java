package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMOResponse;

@Repository
public interface NumberAvailableSeatsSMOResponseRepo extends JpaRepository<NumberAvailableSeatsSMOResponse, Long> {
    APIResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
