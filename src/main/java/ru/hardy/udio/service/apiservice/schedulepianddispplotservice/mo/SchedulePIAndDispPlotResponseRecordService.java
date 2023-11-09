package ru.hardy.udio.service.apiservice.schedulepianddispplotservice.mo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotResponseRecord;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotResponseRecordRepo;

import java.util.List;

@Service
public class SchedulePIAndDispPlotResponseRecordService {

    @Autowired
    private SchedulePIAndDispPlotResponseRecordRepo schedulePIAndDispPlotResponseRecordRepo;

    public void addAll(List<SchedulePIAndDispPlotResponseRecord> schedulePIAndDispPlotResponseRecords){
        schedulePIAndDispPlotResponseRecordRepo.saveAll(schedulePIAndDispPlotResponseRecords);
    }

}
