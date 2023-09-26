package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.SchedulePIAndDispPlotRequestService;
import ru.hardy.udio.service.apiservice.schedulepianddispplotservice.SchedulePIAndDispPlotResponseService;

import java.time.Instant;
import java.util.Date;

@Controller
public class SchedulePIAndDispPlotController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SchedulePIAndDispPlotResponseService schedulePIAndDispPlotResponseService;

    @Autowired
    private SchedulePIAndDispPlotRequestService schedulePIAndDispPlotRequestService;

    @PostMapping("/api/1.1/getSchedulePIAndDispPlot")
    public ResponseEntity<SchedulePIAndDispPlotResponse> registerNumberAvailableSeats(
            @RequestHeader(name = "token") String token,
            @RequestBody SchedulePIAndDispPlotRequest schedulePIAndDispPlotRequest) {

        int codeMO = tokenService.getCodeMOWithToken(token);
        SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponse = new SchedulePIAndDispPlotResponse();

        if (tokenService.checkToken(token)) {
            schedulePIAndDispPlotResponse.setResultRequestCode(201);
            schedulePIAndDispPlotResponse.setDate_beg(Date.from(Instant.now()));
            schedulePIAndDispPlotResponse.setDate_edit(Date.from(Instant.now()));
            schedulePIAndDispPlotResponse.setReqID(schedulePIAndDispPlotRequest.getReqID());
            schedulePIAndDispPlotResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
            schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);

            if (schedulePIAndDispPlotRequestService.getWithReqId(schedulePIAndDispPlotRequest.getReqID(), codeMO).size() > 0){
                schedulePIAndDispPlotResponse.setResultRequestCode(402);
                schedulePIAndDispPlotResponseService.add(schedulePIAndDispPlotResponse);
                return ResponseEntity.ok(schedulePIAndDispPlotResponse);
            }
            if (schedulePIAndDispPlotRequest.getDepartments() == null){
                schedulePIAndDispPlotResponse.setResultRequestCode(400);
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

}
