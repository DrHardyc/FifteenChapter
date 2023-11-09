package ru.hardy.udio.service.apiservice.schedulepianddispplotservice.mo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlot;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotRepo;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class SchedulePIAndDispPlotService {

    @Autowired
    private SchedulePIAndDispPlotRepo schedulePIAndDispPlotRepo;

    public void add(SchedulePIAndDispPlotRequestRecord schedulePIAndDispPlotRequestRecord, int codeMO){
        SchedulePIAndDispPlot schedulePIAndDispPlot = new SchedulePIAndDispPlot();
        schedulePIAndDispPlot.setDate_beg(Date.from(Instant.now()));
        schedulePIAndDispPlot.setDate_edit(Date.from(Instant.now()));
        schedulePIAndDispPlot.setRequestRecord(schedulePIAndDispPlotRequestRecord);
        schedulePIAndDispPlotRepo.save(schedulePIAndDispPlot);
    }

    public void update(SchedulePIAndDispPlot schedulePIAndDispPlot, SchedulePIAndDispPlotRequestRecord schedulePIAndDispPlotRequestRecord){
        schedulePIAndDispPlot.setDate_edit(Date.from(Instant.now()));
        schedulePIAndDispPlot.setRequestRecord(schedulePIAndDispPlotRequestRecord);
        schedulePIAndDispPlotRepo.save(schedulePIAndDispPlot);
    }

    public SchedulePIAndDispPlot getByCodeMOAndCodeDep(int codeMO, int codeDep){
        return schedulePIAndDispPlotRepo.findByCodeMOAndRequestRecord_CodeDep(codeMO, codeDep);
    }

    public List<SchedulePIAndDispPlot> getAllByMO(int codeMO) {
        return schedulePIAndDispPlotRepo.findAllByCodeMO(codeMO);
    }
}
