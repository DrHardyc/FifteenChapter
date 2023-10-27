package ru.hardy.udio.service.apiservice.operatingscheduleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.operatingschedule.*;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.operatingschedulerepo.OperatingScheduleResponseRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.time.Instant;
import java.util.*;

@Service
public class OperatingScheduleResponseService implements APIResponseServiceInterface {

    @Autowired
    private OperatingScheduleResponseRepo operatingScheduleResponseRepo;

    @Autowired
    private OperatingScheduleService operatingScheduleService;

    @Autowired
    private OperatingScheduleResponseRecordService operatingScheduleResponseRecordService;

    @Override
    public void add(APIResponse apiResponse){
        apiResponse.setDateBeg(Date.from(Instant.now()));
        apiResponse.setDateEdit(Date.from(Instant.now()));
        operatingScheduleResponseRepo.save((OperatingScheduleResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        operatingScheduleResponseRepo.saveAll(Collections.singletonList((OperatingScheduleResponse) apiResponses));
    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        OperatingScheduleRequest operatingScheduleRequest = (OperatingScheduleRequest) apiRequest;
        OperatingScheduleResponse operatingScheduleResponse = (OperatingScheduleResponse) apiResponse;

        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<OperatingScheduleResponseRecord> operatingScheduleResponseRecords = new ArrayList<>();

        for (OperatingScheduleRequestRecord departmentRequest : operatingScheduleRequest.getDepartments()){
            departmentRequest.setDateBeg(Date.from(Instant.now()));
            departmentRequest.setDateEdit(Date.from(Instant.now()));

            operatingScheduleService.add(departmentRequest, medicalOrganization.getCodeMO());
            operatingScheduleResponse.setNumberRecordsProcessed(count);
            operatingScheduleResponse.setResultResponseCode(200);
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

    public OperatingScheduleResponse getWithReqId(String reqID, int codeMO) {
        return operatingScheduleResponseRepo.findAllByReqIDAndCodeMO(reqID, codeMO);
    }
}
