package ru.hardy.udio.service.apiservice.schedulepianddispplotservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotRequestRepo;

import java.util.List;

@Service
public class SchedulePIAndDispPlotRequestService {

    @Autowired
    private SchedulePIAndDispPlotRequestRepo schedulePIAndDispPlotRequestRepo;

    public List<SchedulePIAndDispPlotRequest> getWithReqId(String reqID, int codeMO) {
        return schedulePIAndDispPlotRequestRepo.findAllByReqIDAndCodeMO(reqID, codeMO);
    }


    public void add(SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest){

        schedulePIAndDispPlotRequest.getDepartments().forEach(department -> {
            department.setRequest(schedulePIAndDispPlotRequest);
            department.getMonths().forEach(month -> month.setRequestRecord(department));
        });
//        schedulePIAndDispPlotRequestRecordService.addAll(schedulePIAndDispPlotRequest.getDepartments());

        schedulePIAndDispPlotRequestRepo.save(schedulePIAndDispPlotRequest);
    }
}
