package ru.hardy.udio.service.apiservice.schedulepianddispplotservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotRequestRepo;

import java.sql.Date;
import java.time.Instant;

@Service
public class SchedulePIAndDispPlotRequestService {

    @Autowired
    private SchedulePIAndDispPlotRequestRepo schedulePIAndDispPlotRequestRepo;

    public void add(SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest){

        schedulePIAndDispPlotRequest.getDepartments().forEach(department -> {
            department.setRequest(schedulePIAndDispPlotRequest);
            department.setDateBeg(Date.from(Instant.now()));
            department.setDateEdit(Date.from(Instant.now()));
            department.getMonths().forEach(month -> {
                month.setRequestRecord(department);
                month.setDateBeg(Date.from(Instant.now()));
                month.setDateEdit(Date.from(Instant.now()));
            });
        });

        schedulePIAndDispPlotRequestRepo.save(schedulePIAndDispPlotRequest);
    }
}
