package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotRequest;

@Repository
public interface SchedulePIAndDispPlotRequestRepo extends JpaRepository<SchedulePIAndDispPlotRequest, Long> {

}
