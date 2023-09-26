package ru.hardy.udio.service.apiservice.schedulepianddispplotservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotRequestRecordRepo;

import java.util.List;

@Service
public class SchedulePIAndDispPlotRequestRecordService {

    @Autowired
    private SchedulePIAndDispPlotRequestRecordRepo schedulePIAndDispPlotRequestRecordRepo;

    public void addAll(List<SchedulePIAndDispPlotRequestRecord> schedulePIAndDispPlotRequestRecords){
        schedulePIAndDispPlotRequestRecordRepo.saveAll(schedulePIAndDispPlotRequestRecords);
    }
}
