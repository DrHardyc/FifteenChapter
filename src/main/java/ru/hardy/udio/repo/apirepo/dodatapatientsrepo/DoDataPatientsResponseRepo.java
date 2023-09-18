package ru.hardy.udio.repo.apirepo.dodatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsResponse;

@Repository
public interface DoDataPatientsResponseRepo extends JpaRepository<DODataPatientsResponse, Long> {
    DODataPatientsResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
