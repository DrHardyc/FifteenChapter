package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponseRecord;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequest;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponse;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.volumemedicalcareservice.VolumeMedicalCareRequestService;
import ru.hardy.udio.service.apiservice.volumemedicalcareservice.VolumeMedicalCareResponseService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class VolumeMedicalCareController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private VolumeMedicalCareResponseService volumeMedicalCareResponseService;

    @Autowired
    private VolumeMedicalCareRequestService volumeMedicalCareRequestService;

    @PostMapping("/api/1.1/getVolumeMedicalCare")
    public ResponseEntity<VolumeMedicalCareResponse> registerVolumeMedicalCare(
            @RequestHeader(name = "token") String token,
            @RequestBody VolumeMedicalCareRequest volumeMedicalCareRequest) {

        int codeMO = tokenService.getCodeMOWithToken(token);
        VolumeMedicalCareResponse volumeMedicalCareResponse = new VolumeMedicalCareResponse();

        if (tokenService.checkToken(token)) {

            if (volumeMedicalCareResponseService.getWithReqId(volumeMedicalCareRequest.getReqID(), codeMO) != null){
                volumeMedicalCareResponse.setDate_beg(Date.from(Instant.now()));
                volumeMedicalCareResponse.setDate_edit(Date.from(Instant.now()));
                volumeMedicalCareResponse.setReqID(volumeMedicalCareRequest.getReqID());
                volumeMedicalCareResponse.setResultRequestCode(402);
                volumeMedicalCareResponseService.add(volumeMedicalCareResponse);
                return ResponseEntity.ok(volumeMedicalCareResponse);
            }

            volumeMedicalCareResponse.setResultRequestCode(201);
            volumeMedicalCareResponse.setDate_beg(Date.from(Instant.now()));
            volumeMedicalCareResponse.setDate_edit(Date.from(Instant.now()));
            volumeMedicalCareResponse.setReqID(volumeMedicalCareRequest.getReqID());
            volumeMedicalCareResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
            volumeMedicalCareResponseService.add(volumeMedicalCareResponse);

            if (volumeMedicalCareRequest.getDepartments() == null){
                volumeMedicalCareResponse.setResultRequestCode(400);
                volumeMedicalCareResponseService.add(volumeMedicalCareResponse);
                return ResponseEntity.ok(volumeMedicalCareResponse);
            }

            try {
                volumeMedicalCareRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                volumeMedicalCareRequest.setDateBeg(Date.from(Instant.now()));
                volumeMedicalCareRequest.setDateEdit(Date.from(Instant.now()));
                volumeMedicalCareRequestService.add(volumeMedicalCareRequest);

                volumeMedicalCareResponse.setResultRequestCode(200);
                volumeMedicalCareResponseService.add(volumeMedicalCareResponse);

                return ResponseEntity.ok(volumeMedicalCareResponseService
                        .processing(volumeMedicalCareRequest, volumeMedicalCareResponse,
                                tokenService.getCodeMOWithToken(token)));

            } catch (Exception e){
                volumeMedicalCareResponse.setResultRequestCode(400);
                volumeMedicalCareResponseService.add(volumeMedicalCareResponse);
            }

        } else {
            volumeMedicalCareResponse.setResultRequestCode(403);
            volumeMedicalCareResponseService.add(volumeMedicalCareResponse);
        }
        return ResponseEntity.ok(volumeMedicalCareResponse);
    }

    @PostMapping("/api/test/getVolumeMedicalCare")
    public ResponseEntity<VolumeMedicalCareResponse> registerVolumeMedicalCareTest(
            @RequestHeader(name = "token") String token,
            @RequestBody VolumeMedicalCareRequest volumeMedicalCareRequest) {

        VolumeMedicalCareResponse volumeMedicalCareResponse = new VolumeMedicalCareResponse();

        if (tokenService.checkToken(token)) {
            if (volumeMedicalCareRequest.getDepartments().get(0).getCodeDep() == 55){
                List<VolumeMedicalCareResponseRecord> volumeMedicalCareResponseRecords = new ArrayList<>();
                volumeMedicalCareRequest.getDepartments().forEach(department -> {
                    volumeMedicalCareResponseRecords.add(new VolumeMedicalCareResponseRecord(department,
                            volumeMedicalCareResponse, 500, "Запись успешно обработана"));
                });

                volumeMedicalCareResponse.setResultRequestCode(200);
                volumeMedicalCareResponse.setReqID(volumeMedicalCareRequest.getReqID());
                volumeMedicalCareResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                volumeMedicalCareResponse.setNumberRecordsProcessed(2);
                volumeMedicalCareResponse.setDepartments(volumeMedicalCareResponseRecords);
            }
        } else {
            volumeMedicalCareResponse.setResultRequestCode(403);
            volumeMedicalCareResponseService.add(volumeMedicalCareResponse);
        }
        return ResponseEntity.ok(volumeMedicalCareResponse);
    }


    @GetMapping("/api/1.1/getVolumeMedicalCare/{reqID}")
    public ResponseEntity<VolumeMedicalCareResponse> getVolumeMedicalCare(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        VolumeMedicalCareResponse volumeMedicalCareResponse = new VolumeMedicalCareResponse();
        if (tokenService.checkToken(token)) {
            try {
                VolumeMedicalCareResponse volumeMedicalCareResponseFromDB =
                        volumeMedicalCareResponseService.getWithReqId(reqID, codeMO);
                if (volumeMedicalCareResponseFromDB != null){
                    return ResponseEntity.ok(volumeMedicalCareResponseFromDB);
                } else {
                    volumeMedicalCareResponse.setResultRequestCode(401);
                }
            } catch (Exception e){
                volumeMedicalCareResponse.setResultRequestCode(400);
            }
        } else {
            volumeMedicalCareResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(volumeMedicalCareResponse);
    }
}

