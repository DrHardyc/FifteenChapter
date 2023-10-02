package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlot;

@Repository
public interface SchedulePIAndDispPlotRepo extends JpaRepository<SchedulePIAndDispPlot, Long> {
    SchedulePIAndDispPlot findByCodeMOAndAndDepartmentRequest_CodeDep(int codeMO, int codeDep);

}
