package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequest;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponse;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.APIRequestService;
import ru.hardy.udio.service.apiservice.volumemedicalcareservice.VolumeMedicalCareResponseService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VolumeMedicalCareController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private VolumeMedicalCareResponseService volumeMedicalCareResponseService;

    @Autowired
    private APIRequestService apiRequestService;

    @PostMapping("/api/1.1/setVolumeMedicalCare")
    public ResponseEntity<VolumeMedicalCareResponse> registerVolumeMedicalCare(
            @RequestHeader(name = "token") String token,
            @RequestBody VolumeMedicalCareRequest volumeMedicalCareRequest) {

        return ResponseEntity
                .ok((VolumeMedicalCareResponse) apiRequestService
                        .acceptance(token, volumeMedicalCareRequest));
    }

    @PostMapping("/api/test/setVolumeMedicalCare")
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

                volumeMedicalCareResponse.setResultResponseCode(200);
                volumeMedicalCareResponse.setReqID(volumeMedicalCareRequest.getReqID());
                volumeMedicalCareResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                volumeMedicalCareResponse.setNumberRecordsProcessed(2);
                volumeMedicalCareResponse.setDepartments(volumeMedicalCareResponseRecords);
            }
        } else {
            volumeMedicalCareResponse.setResultResponseCode(403);
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
                    volumeMedicalCareResponse.setResultResponseCode(401);
                }
            } catch (Exception e){
                volumeMedicalCareResponse.setResultResponseCode(400);
            }
        } else {
            volumeMedicalCareResponse.setResultResponseCode(403);
        }
        return ResponseEntity.ok(volumeMedicalCareResponse);
    }
}

