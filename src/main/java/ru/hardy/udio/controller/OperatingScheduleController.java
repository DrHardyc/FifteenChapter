package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponse;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.APIRequestService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleResponseService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OperatingScheduleController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OperatingScheduleResponseService operatingScheduleResponseService;

    @Autowired
    private APIRequestService apiRequestService;


    @PostMapping("/api/1.1/setOperatingSchedule")
    public ResponseEntity<OperatingScheduleResponse> registerOperatingSchedule(
            @RequestHeader(name = "token") String token,
            @RequestBody OperatingScheduleRequest operatingScheduleRequest){
        return ResponseEntity
                .ok((OperatingScheduleResponse) apiRequestService
                        .acceptance(token, operatingScheduleRequest));
    }

    @PostMapping("/api/test/setOperatingSchedule")
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
                operatingScheduleResponse.setResultResponseCode(200);
                operatingScheduleResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                operatingScheduleResponse.setNumberRecordsProcessed(count);

            } catch (Exception e){
                operatingScheduleResponse.setResultResponseCode(400);
                operatingScheduleResponseService.add(operatingScheduleResponse);
            }
        } else {
            operatingScheduleResponse.setResultResponseCode(403);
            operatingScheduleResponseService.add(operatingScheduleResponse);
        }
        return ResponseEntity.ok(operatingScheduleResponse);
    }

    @GetMapping("/api/1.1/getOperatingSchedule/{reqID}")
    public ResponseEntity<OperatingScheduleResponse> getOperatingSchedule(
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
                    operatingScheduleResponse.setResultResponseCode(401);
                }
            } catch (Exception e){
                operatingScheduleResponse.setResultResponseCode(400);
            }
        } else {
            operatingScheduleResponse.setResultResponseCode(403);
        }
        return ResponseEntity.ok(operatingScheduleResponse);
    }

}
