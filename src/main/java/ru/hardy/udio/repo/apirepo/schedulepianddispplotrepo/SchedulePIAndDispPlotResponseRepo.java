package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotResponse;

@Repository
public interface SchedulePIAndDispPlotResponseRepo extends JpaRepository<SchedulePIAndDispPlotResponse, Long> {

    SchedulePIAndDispPlotResponse findSchedulePIAndDispPlotResponseByReqIDAndCodeMO(String reqID, int codeMO);
}
