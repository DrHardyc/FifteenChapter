package ru.hardy.udio.repo.apirepo.choosingmorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;

@Repository
public interface ChoosingMOResponseRepo extends JpaRepository<ChoosingMOResponse, Long> {

    ChoosingMOResponse findChoosingMOResponseByReqIDAndCodeMO(String reqID, int codeMO);
}
