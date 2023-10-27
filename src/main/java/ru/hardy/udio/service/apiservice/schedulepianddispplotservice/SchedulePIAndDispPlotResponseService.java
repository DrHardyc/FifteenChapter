package ru.hardy.udio.service.apiservice.schedulepianddispplotservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.*;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotResponseRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SchedulePIAndDispPlotResponseService implements APIResponseServiceInterface {

    @Autowired
    private SchedulePIAndDispPlotResponseRepo schedulePIAndDispPlotResponseRepo;

    @Autowired
    private SchedulePIAndDispPlotService schedulePIAndDispPlotService;

    @Autowired
    private SchedulePIAndDispPlotResponseRecordService schedulePIAndDispPlotResponseRecordService;

    @Override
    public void add(APIResponse apiResponse) {
        apiResponse.setDateBeg(Date.from(Instant.now()));
        apiResponse.setDateEdit(Date.from(Instant.now()));
        schedulePIAndDispPlotResponseRepo.save((SchedulePIAndDispPlotResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        schedulePIAndDispPlotResponseRepo
                .saveAll(Collections
                    .singletonList((SchedulePIAndDispPlotResponse) apiResponses));
    }

    @Override
    public SchedulePIAndDispPlotResponse processing(APIRequest apiRequest,
                                                    APIResponse apiResponse,
                                                    int codeMO) {
        SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest = (SchedulePIAndDispPlotRequest) apiRequest;
        SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponse = (SchedulePIAndDispPlotResponse) apiResponse;
        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<SchedulePIAndDispPlotResponseRecord> schedulePIAndDispPlotResponseRecords = new ArrayList<>();

        for (SchedulePIAndDispPlotRequestRecord departmentRequest : schedulePIAndDispPlotRequest.getDepartments()){
            SchedulePIAndDispPlot schedulePIAndDispPlot = schedulePIAndDispPlotService.getByCodeMOAndCodeDep(codeMO, departmentRequest.getCodeDep());
            if (schedulePIAndDispPlot != null){
                schedulePIAndDispPlotService.update(schedulePIAndDispPlot, departmentRequest);
                errMess = "Запись успешно обновлена";
            } else {
                schedulePIAndDispPlotService.add(departmentRequest, codeMO);
            }
            schedulePIAndDispPlotResponse.setResultResponseCode(200);
            schedulePIAndDispPlotResponse.setNumberRecordsProcessed(count);
            add(schedulePIAndDispPlotResponse);

            schedulePIAndDispPlotResponseRecords.add(new SchedulePIAndDispPlotResponseRecord(departmentRequest,
                    schedulePIAndDispPlotResponse,
                    errCode, errMess));
            count++;

        }

        schedulePIAndDispPlotResponseRecordService.addAll(schedulePIAndDispPlotResponseRecords);
        schedulePIAndDispPlotResponse.setDepartments(schedulePIAndDispPlotResponseRecords);
        schedulePIAndDispPlotResponse.setNumberRecordsProcessed(count);
        schedulePIAndDispPlotResponse.setReqID(schedulePIAndDispPlotRequest.getReqID());
        add(schedulePIAndDispPlotResponse);

        return schedulePIAndDispPlotResponse;
    }

    public SchedulePIAndDispPlotResponse getWithReqId(String reqID, int codeMO) {
        return schedulePIAndDispPlotResponseRepo.findSchedulePIAndDispPlotResponseByReqIDAndCodeMO(reqID, codeMO);
    }
}
