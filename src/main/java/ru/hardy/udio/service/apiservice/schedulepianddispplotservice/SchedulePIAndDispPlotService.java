package ru.hardy.udio.service.apiservice.schedulepianddispplotservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlot;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class SchedulePIAndDispPlotService {

    @Autowired
    private SchedulePIAndDispPlotRepo schedulePIAndDispPlotRepo;

    public void add(SchedulePIAndDispPlotRequestRecord schedulePIAndDispPlotRequestRecord, int codeMO){
        SchedulePIAndDispPlot schedulePIAndDispPlotFromDB = schedulePIAndDispPlotRepo.findByCodeMOAndAndDepartmentRequest_DepartmentCode(codeMO,
         schedulePIAndDispPlotRequestRecord.getDepartmentCode());
        if (schedulePIAndDispPlotFromDB != null){
            schedulePIAndDispPlotFromDB.setDate_edit(Date.from(Instant.now()));
            schedulePIAndDispPlotFromDB.setDepartmentRequest(schedulePIAndDispPlotRequestRecord);
            schedulePIAndDispPlotRepo.save(schedulePIAndDispPlotFromDB);
        } else {
            SchedulePIAndDispPlot schedulePIAndDispPlot = new SchedulePIAndDispPlot();
            schedulePIAndDispPlot.setCodeMO(codeMO);
            schedulePIAndDispPlot.setDate_beg(Date.from(Instant.now()));
            schedulePIAndDispPlot.setDate_edit(Date.from(Instant.now()));
            schedulePIAndDispPlot.setDepartmentRequest(schedulePIAndDispPlotRequestRecord);
            schedulePIAndDispPlotRepo.save(schedulePIAndDispPlot);
        }
    }
}
