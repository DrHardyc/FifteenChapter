package ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;

@Repository
public interface SchedulePIAndDispPlotRequestRecordRepo extends JpaRepository<SchedulePIAndDispPlotRequestRecord, Long> {
}
