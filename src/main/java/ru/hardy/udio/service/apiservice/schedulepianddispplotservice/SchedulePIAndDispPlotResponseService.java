package ru.hardy.udio.service.apiservice.schedulepianddispplotservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequestRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponseRecord;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.SchedulePIAndDispPlotResponseRepo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SchedulePIAndDispPlotResponseService {

    @Autowired
    private SchedulePIAndDispPlotResponseRepo schedulePIAndDispPlotResponseRepo;

    @Autowired
    private SchedulePIAndDispPlotService schedulePIAndDispPlotService;

    @Autowired
    private SchedulePIAndDispPlotResponseRecordService schedulePIAndDispPlotResponseRecordService;

    public void add(SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponse) {
        schedulePIAndDispPlotResponseRepo.save(schedulePIAndDispPlotResponse);
    }

    public SchedulePIAndDispPlotResponse processing(SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest,
                                                    SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponse,
                                                    int codeMO) {
        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<SchedulePIAndDispPlotResponseRecord> schedulePIAndDispPlotResponseRecords = new ArrayList<>();

        for (SchedulePIAndDispPlotRequestRecord departmentRequest : schedulePIAndDispPlotRequest.getDepartments()){
            schedulePIAndDispPlotService.add(departmentRequest, codeMO);
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
