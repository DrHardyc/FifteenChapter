package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleRequestService;
import ru.hardy.udio.service.apiservice.operatingscheduleservice.OperatingScheduleResponseService;

import java.time.Instant;
import java.util.Date;

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
            @RequestBody OperatingScheduleRequest operatingScheduleRequest
    ){
        int codeMO = tokenService.getCodeMOWithToken(token);
        OperatingScheduleResponse operatingScheduleResponse = new OperatingScheduleResponse();

        if (tokenService.checkToken(token)) {
            operatingScheduleResponse.setResultRequestCode(201);
            operatingScheduleResponse.setDate_beg(Date.from(Instant.now()));
            operatingScheduleResponse.setDate_edit(Date.from(Instant.now()));
            operatingScheduleResponse.setReqID(operatingScheduleRequest.getReqID());
            operatingScheduleResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
            operatingScheduleResponseService.add(operatingScheduleResponse);

            if (operatingScheduleRequestService.getWithReqId(operatingScheduleRequest.getReqID(), codeMO).size() > 0){
                operatingScheduleResponse.setResultRequestCode(402);
                operatingScheduleResponseService.add(operatingScheduleResponse);
                return ResponseEntity.ok(operatingScheduleResponse);
            }
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

}
