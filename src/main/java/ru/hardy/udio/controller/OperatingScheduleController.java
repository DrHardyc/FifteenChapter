package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponse;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleRequestService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleResponseService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class OperatingScheduleController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OperatingScheduleResponseService operatingScheduleResponseService;

    @Autowired
    private OperatingScheduleRequestService operatingScheduleRequestService;


    @PostMapping("/api/1.1/getOperatingSchedule")
    public ResponseEntity<OperatingScheduleResponse> registerOperatingSchedule(
            @RequestHeader(name = "token") String token,
            @RequestBody OperatingScheduleRequest operatingScheduleRequest)
    {
        int codeMO = tokenService.getCodeMOWithToken(token);
        OperatingScheduleResponse operatingScheduleResponse = new OperatingScheduleResponse();

        if (tokenService.checkToken(token)) {
            if (operatingScheduleResponseService.getWithReqId(operatingScheduleRequest.getReqID(), codeMO) != null){
                operatingScheduleResponse.setResultRequestCode(402);
                operatingScheduleResponseService.add(operatingScheduleResponse);
                return ResponseEntity.ok(operatingScheduleResponse);
            }

            operatingScheduleResponse.setResultRequestCode(201);
            operatingScheduleResponse.setDate_beg(Date.from(Instant.now()));
            operatingScheduleResponse.setDate_edit(Date.from(Instant.now()));
            operatingScheduleResponse.setReqID(operatingScheduleRequest.getReqID());
            operatingScheduleResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
            operatingScheduleResponseService.add(operatingScheduleResponse);

            if (operatingScheduleRequest.getDepartments() == null){
                operatingScheduleResponse.setResultRequestCode(400);
                operatingScheduleResponseService.add(operatingScheduleResponse);
                return ResponseEntity.ok(operatingScheduleResponse);
            }

            try {
                operatingScheduleRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                operatingScheduleRequest.setDate_beg(Date.from(Instant.now()));
                operatingScheduleRequest.setDate_edit(Date.from(Instant.now()));
                operatingScheduleRequestService.add(operatingScheduleRequest);

                operatingScheduleResponse.setResultRequestCode(200);
                operatingScheduleResponseService.add(operatingScheduleResponse);

                return ResponseEntity.ok(operatingScheduleResponseService
                        .processing(operatingScheduleRequest, operatingScheduleResponse,
                                tokenService.getCodeMOWithToken(token)));

            } catch (Exception e){
                operatingScheduleResponse.setResultRequestCode(400);
                operatingScheduleResponseService.add(operatingScheduleResponse);
            }
        } else {
            operatingScheduleResponse.setResultRequestCode(403);
            operatingScheduleResponseService.add(operatingScheduleResponse);
        }
        return ResponseEntity.ok(operatingScheduleResponse);

    }

    @PostMapping("/api/test/getOperatingSchedule")
    public ResponseEntity<OperatingScheduleResponse> registerOperatingScheduleTest(
            @RequestHeader(name = "token") String token,
            @RequestBody OperatingScheduleRequest operatingScheduleRequest)
    {
        OperatingScheduleResponse operatingScheduleResponse = new OperatingScheduleResponse();
        List<OperatingScheduleResponseRecord> operatingScheduleResponseRecords = new ArrayList<>();
        int count = 0;
        if (tokenService.checkToken(token) && operatingScheduleRequest.getDepartments().get(0).getCodeDep() == 55) {
            try {
                operatingScheduleRequest.getDepartments().forEach(department -> {
                    operatingScheduleResponseRecords.add(new OperatingScheduleResponseRecord(department, operatingScheduleResponse,
                            500, "Запись успешно обработана"));
                });
                operatingScheduleResponse.setDepartments(operatingScheduleResponseRecords);
                operatingScheduleResponse.setResultRequestCode(200);
                operatingScheduleResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                operatingScheduleResponse.setNumberRecordsProcessed(count);

            } catch (Exception e){
                operatingScheduleResponse.setResultRequestCode(400);
                operatingScheduleResponseService.add(operatingScheduleResponse);
            }
        } else {
            operatingScheduleResponse.setResultRequestCode(403);
            operatingScheduleResponseService.add(operatingScheduleResponse);
        }
        return ResponseEntity.ok(operatingScheduleResponse);
    }

    @GetMapping("/api/1.1/getOperatingSchedule/{reqID}")
    public ResponseEntity<OperatingScheduleResponse> getgetOperatingSchedule(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        OperatingScheduleResponse operatingScheduleResponse = new OperatingScheduleResponse();
        if (tokenService.checkToken(token)) {
            try {
                OperatingScheduleResponse operatingScheduleResponseFromDB
                        = operatingScheduleResponseService.getWithReqId(reqID, codeMO);
                if (operatingScheduleResponseFromDB != null){
                    return ResponseEntity.ok(operatingScheduleResponseFromDB);
                } else {
                    operatingScheduleResponse.setResultRequestCode(401);
                }
            } catch (Exception e){
                operatingScheduleResponse.setResultRequestCode(400);
            }
        } else {
            operatingScheduleResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(operatingScheduleResponse);
    }

}
