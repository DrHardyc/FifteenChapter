package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.smo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMORequest;

@Repository
public interface SchedulePIAndDispPlotSMORequestRepo extends JpaRepository<SchedulePIAndDispPlotSMORequest, Long> {
}
