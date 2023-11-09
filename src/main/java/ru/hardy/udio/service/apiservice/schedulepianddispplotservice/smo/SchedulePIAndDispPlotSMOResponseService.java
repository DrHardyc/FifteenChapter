package ru.hardy.udio.service.apiservice.schedulepianddispplotservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlot;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.MonthEventSMO;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMORequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMOResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMOResponseRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.smo.SchedulePIAndDispPlotSMOResponseRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.mo.SchedulePIAndDispPlotService;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulePIAndDispPlotSMOResponseService implements APIResponseServiceInterface {

    @Autowired
    private SchedulePIAndDispPlotSMOResponseRepo schedulePIAndDispPlotSMOResponseRepo;

    @Autowired
    private SchedulePIAndDispPlotService schedulePIAndDispPlotService;

    @Override
    public void add(APIResponse apiResponse) {
        schedulePIAndDispPlotSMOResponseRepo.save((SchedulePIAndDispPlotSMOResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {

    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        SchedulePIAndDispPlotSMORequest schedulePIAndDispPlotSMORequest = (SchedulePIAndDispPlotSMORequest) apiRequest;
        SchedulePIAndDispPlotSMOResponse schedulePIAndDispPlotSMOResponse = (SchedulePIAndDispPlotSMOResponse) apiResponse;
        List<SchedulePIAndDispPlotSMOResponseRecord> schedulePIAndDispPlotSMOResponseRecords = new ArrayList<>();
        List<SchedulePIAndDispPlot> schedulePIAndDispPlots = schedulePIAndDispPlotService
                .getAllByMO(schedulePIAndDispPlotSMORequest.getCodeMO());
        schedulePIAndDispPlots.forEach(schedulePIAndDispPlot -> {
            List<MonthEventSMO> monthEventSMOS = new ArrayList<>();
            schedulePIAndDispPlot.getRequestRecord().getMonths().forEach(monthEvent -> {
                monthEventSMOS.add(new MonthEventSMO(monthEvent));
            });
            schedulePIAndDispPlotSMOResponseRecords.add(new SchedulePIAndDispPlotSMOResponseRecord(schedulePIAndDispPlot.getRequestRecord(),
                    schedulePIAndDispPlotSMOResponse, monthEventSMOS));
        });
        schedulePIAndDispPlotSMOResponse.setDepartments(schedulePIAndDispPlotSMOResponseRecords);
        schedulePIAndDispPlotSMOResponse.setResultResponseCode(200);
        schedulePIAndDispPlotSMOResponse.setCodeMO(schedulePIAndDispPlotSMORequest.getCodeMO());
        schedulePIAndDispPlotSMOResponse.setDateBeg(Date.from(Instant.now()));
        schedulePIAndDispPlotSMOResponse.setDateEdit(Date.from(Instant.now()));
        add(schedulePIAndDispPlotSMOResponse);
        return schedulePIAndDispPlotSMOResponse;
    }

    public APIResponse getWithReqId(String reqID, int codeMO) {
        return schedulePIAndDispPlotSMOResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }
}
