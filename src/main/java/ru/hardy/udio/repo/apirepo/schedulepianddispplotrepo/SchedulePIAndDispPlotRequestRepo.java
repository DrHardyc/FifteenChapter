package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;

import java.util.List;

@Repository
public interface SchedulePIAndDispPlotRequestRepo extends JpaRepository<SchedulePIAndDispPlotRequest, Long> {
    List<SchedulePIAndDispPlotRequest> findAllByReqIDAndCodeMO(String reqID, int codeMO);
}
