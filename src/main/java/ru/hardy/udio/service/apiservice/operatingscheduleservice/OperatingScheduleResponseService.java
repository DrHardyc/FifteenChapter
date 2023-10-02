package ru.hardy.udio.service.apiservice.operatingscheduleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.operatingschedule.*;
import ru.hardy.udio.repo.apirepo.operatingschedulerepo.OperatingScheduleResponseRepo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OperatingScheduleResponseService {

    @Autowired
    private OperatingScheduleResponseRepo operatingScheduleResponseRepo;

    @Autowired
    private OperatingScheduleService operatingScheduleService;

    @Autowired
    private OperatingScheduleResponseRecordService operatingScheduleResponseRecordService;

    public void add(OperatingScheduleResponse operatingScheduleResponse){
        operatingScheduleResponseRepo.save(operatingScheduleResponse);
    }

    public OperatingScheduleResponse processing(OperatingScheduleRequest operatingScheduleRequest,
                             OperatingScheduleResponse operatingScheduleResponse,
                             int codeMO) {
        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<OperatingScheduleResponseRecord> operatingScheduleResponseRecords = new ArrayList<>();

        for (OperatingScheduleRequestRecord departmentRequest : operatingScheduleRequest.getDepartments()){
            departmentRequest.setDate_beg(Date.from(Instant.now()));
            departmentRequest.setDate_edit(Date.from(Instant.now()));

            operatingScheduleService.add(departmentRequest, codeMO);
            operatingScheduleResponse.setNumberRecordsProcessed(count);
            add(operatingScheduleResponse);

            operatingScheduleResponseRecords.add(new OperatingScheduleResponseRecord(departmentRequest,
                    operatingScheduleResponse,
                    errCode, errMess));
            count++;

        }

        operatingScheduleResponseRecordService.addAll(operatingScheduleResponseRecords);
        operatingScheduleResponse.setDepartments(operatingScheduleResponseRecords);
        operatingScheduleResponse.setNumberRecordsProcessed(count);
        operatingScheduleResponse.setReqID(operatingScheduleRequest.getReqID());
        add(operatingScheduleResponse);

        return operatingScheduleResponse;
    }
}
