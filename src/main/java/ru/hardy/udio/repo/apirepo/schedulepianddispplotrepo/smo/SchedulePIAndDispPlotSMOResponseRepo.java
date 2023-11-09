package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMOResponse;

@Repository
public interface SchedulePIAndDispPlotSMOResponseRepo extends JpaRepository<SchedulePIAndDispPlotSMOResponse, Long> {
    APIResponse findByReqIDAndCodeMO(String reqID, int codeMO);
}
