package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotResponseRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMORequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMOResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.APIRequestService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.mo.SchedulePIAndDispPlotResponseService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SchedulePIAndDispPlotController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SchedulePIAndDispPlotResponseService schedulePIAndDispPlotResponseService;

    @Autowired
    private APIRequestService apiRequestService;

    @PostMapping("/api/1.1/setSchedulePIAndDispPlot")
    public ResponseEntity<SchedulePIAndDispPlotResponse> registerSchedulePIAndDispPlot(
            @RequestHeader(name = "token") String token,
            @RequestBody SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest) {

        return ResponseEntity
                .ok((SchedulePIAndDispPlotResponse) apiRequestService
                        .acceptance(token, schedulePIAndDispPlotRequest));
    }

    @PostMapping("/api/1.1/getSchedulePIAndDispPlot")
    public ResponseEntity<SchedulePIAndDispPlotSMOResponse> getSchedulePIAndDispPlot(
            @RequestHeader(name = "token") String token,
            @RequestBody SchedulePIAndDispPlotSMORequest schedulePIAndDispPlotSMORequest) {

        return ResponseEntity
                .ok((SchedulePIAndDispPlotSMOResponse) apiRequestService
                        .acceptance(token, schedulePIAndDispPlotSMORequest));
    }

    @PostMapping("/api/test/setSchedulePIAndDispPlot")
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

                schedulePIAndDispPlotResponse.setResultResponseCode(200);
                schedulePIAndDispPlotResponse.setReqID(schedulePIAndDispPlotRequest.getReqID());
                schedulePIAndDispPlotResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                schedulePIAndDispPlotResponse.setNumberRecordsProcessed(2);
                schedulePIAndDispPlotResponse.setDepartments(schedulePIAndDispPlotResponsesRecords);
            }
        } else {
            schedulePIAndDispPlotResponse.setResultResponseCode(403);
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
                    schedulePIAndDispPlotResponse.setResultResponseCode(401);
                }
            } catch (Exception e){
                schedulePIAndDispPlotResponse.setResultResponseCode(400);
            }
        } else {
            schedulePIAndDispPlotResponse.setResultResponseCode(403);
        }
        return ResponseEntity.ok(schedulePIAndDispPlotResponse);
    }
}
