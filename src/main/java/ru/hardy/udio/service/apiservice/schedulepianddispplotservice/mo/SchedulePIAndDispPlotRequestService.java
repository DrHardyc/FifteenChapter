package ru.hardy.udio.service.apiservice.schedulepianddispplotservice.mo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.sql.Date;
import java.time.Instant;

@Service
public class SchedulePIAndDispPlotRequestService implements APIRequestServiceInterface {

    @Autowired
    private SchedulePIAndDispPlotRequestRepo schedulePIAndDispPlotRequestRepo;

    public void add(APIRequest apiRequest){
        SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest = (SchedulePIAndDispPlotRequest) apiRequest;
        schedulePIAndDispPlotRequest.setDateBeg(Date.from(Instant.now()));
        schedulePIAndDispPlotRequest.setDateEdit(Date.from(Instant.now()));
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

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((SchedulePIAndDispPlotRequest) apiRequest).getDepartments() != null;
    }
}
