package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.SchedulePIAndDispPlotRequestService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.SchedulePIAndDispPlotResponseService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class SchedulePIAndDispPlotController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SchedulePIAndDispPlotResponseService schedulePIAndDispPlotResponseService;

    @Autowired
    private SchedulePIAndDispPlotRequestService schedulePIAndDispPlotRequestService;

    @PostMapping("/api/1.1/getSchedulePIAndDispPlot")
    public ResponseEntity<SchedulePIAndDispPlotResponse> registerSchedulePIAndDispPlot(
            @RequestHeader(name = "token") String token,
            @RequestBody SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest) {

        int codeMO = tokenService.getCodeMOWithToken(token);
        SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponse = new SchedulePIAndDispPlotResponse();

        if (tokenService.checkToken(token)) {

            if (schedulePIAndDispPlotResponseService.getWithReqId(schedulePIAndDispPlotRequest.getReqID(), codeMO) != null){
                schedulePIAndDispPlotResponse.setResultRequestCode(402);
                schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);
                return ResponseEntity.ok(schedulePIAndDispPlotResponse);
            }

            schedulePIAndDispPlotResponse.setResultRequestCode(201);
            schedulePIAndDispPlotResponse.setDate_beg(Date.from(Instant.now()));
            schedulePIAndDispPlotResponse.setDate_edit(Date.from(Instant.now()));
            schedulePIAndDispPlotResponse.setReqID(schedulePIAndDispPlotRequest.getReqID());
            schedulePIAndDispPlotResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
            schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);

            if (schedulePIAndDispPlotRequest.getDepartments() == null){
                schedulePIAndDispPlotResponse.setResultRequestCode(400);
                schedulePIAndDispPlotResponse.setDate_beg(Date.from(Instant.now()));
                schedulePIAndDispPlotResponse.setDate_edit(Date.from(Instant.now()));
                schedulePIAndDispPlotResponse.setReqID(schedulePIAndDispPlotRequest.getReqID());
                schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);
                return ResponseEntity.ok(schedulePIAndDispPlotResponse);
            }

            try {
                schedulePIAndDispPlotRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                schedulePIAndDispPlotRequest.setDate_beg(Date.from(Instant.now()));
                schedulePIAndDispPlotRequest.setDate_edit(Date.from(Instant.now()));
                schedulePIAndDispPlotRequestService.add(schedulePIAndDispPlotRequest);

                schedulePIAndDispPlotResponse.setResultRequestCode(200);
                schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);

                return ResponseEntity.ok(schedulePIAndDispPlotResponseService
                        .processing(schedulePIAndDispPlotRequest, schedulePIAndDispPlotResponse,
                                tokenService.getCodeMOWithToken(token)));

            } catch (Exception e){
                schedulePIAndDispPlotResponse.setResultRequestCode(400);
                schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);
            }

        } else {
            schedulePIAndDispPlotResponse.setResultRequestCode(403);
            schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);
        }
        return ResponseEntity.ok(schedulePIAndDispPlotResponse);
    }

    @PostMapping("/api/test/getSchedulePIAndDispPlot")
    public ResponseEntity<SchedulePIAndDispPlotResponse> registerSchedulePIAndDispPlotTest(
            @RequestHeader(name = "token") String token,
            @RequestBody SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest) {

        SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponse = new SchedulePIAndDispPlotResponse();

        if (tokenService.checkToken(token)) {
            if (schedulePIAndDispPlotRequest.getDepartments().get(0).getCodeDep() == 55){
                List<SchedulePIAndDispPlotResponseRecord> schedulePIAndDispPlotResponsesRecords = new ArrayList<>();
                schedulePIAndDispPlotRequest.getDepartments().forEach(department -> {
                    schedulePIAndDispPlotResponsesRecords.add(new SchedulePIAndDispPlotResponseRecord(department,
                            schedulePIAndDispPlotResponse, 500, "Запись успешно обработана"));
                });

                schedulePIAndDispPlotResponse.setResultRequestCode(200);
                schedulePIAndDispPlotResponse.setReqID(schedulePIAndDispPlotRequest.getReqID());
                schedulePIAndDispPlotResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                schedulePIAndDispPlotResponse.setNumberRecordsProcessed(2);
                schedulePIAndDispPlotResponse.setDepartments(schedulePIAndDispPlotResponsesRecords);
            }
        } else {
            schedulePIAndDispPlotResponse.setResultRequestCode(403);
            schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);
        }
        return ResponseEntity.ok(schedulePIAndDispPlotResponse);
    }

    @GetMapping("/api/1.1/getSchedulePIAndDispPlot/{reqID}")
    public ResponseEntity<SchedulePIAndDispPlotResponse> getSchedulePIAndDispPlot(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponse = new SchedulePIAndDispPlotResponse();
        if (tokenService.checkToken(token)) {
            try {
                SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponseFromDB =
                        schedulePIAndDispPlotResponseService.getWithReqId(reqID, codeMO);
                if (schedulePIAndDispPlotResponseFromDB != null){
                    return ResponseEntity.ok(schedulePIAndDispPlotResponseFromDB);
                } else {
                    schedulePIAndDispPlotResponse.setResultRequestCode(401);
                }
            } catch (Exception e){
                schedulePIAndDispPlotResponse.setResultRequestCode(400);
            }
        } else {
            schedulePIAndDispPlotResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(schedulePIAndDispPlotResponse);
    }
}
