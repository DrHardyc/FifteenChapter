package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsRequest;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientsRequestRecordService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientsRequestService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientsResponseService;

import java.time.Instant;
import java.util.Date;

@RestController
public class PADataPatientsController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PADataPatientsResponseService PADataPatientsResponseService;

    @Autowired
    private PADataPatientsRequestService paDataPatientsRequestService;

    @PostMapping("/api/1.1/getPADataPatients")
    public ResponseEntity<PADataPatientsResponse> registerChoosingMO(
            @RequestHeader(name = "token") String token,
            @RequestBody PADataPatientsRequest doDataPatientsRequest) {

        PADataPatientsResponse PADataPatientsResponse = new PADataPatientsResponse();
        PADataPatientsResponse.setResultRequestCode(201);
        PADataPatientsResponseService.add(PADataPatientsResponse);

        if (tokenService.checkToken(token)) {
            PADataPatientsResponse PADataPatientsResponseFromDB =
                    PADataPatientsResponseService.getWithReqId(doDataPatientsRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (PADataPatientsResponseFromDB != null){
                PADataPatientsResponse.setResultRequestCode(402);
                PADataPatientsResponseService.add(PADataPatientsResponse);
                return ResponseEntity.ok(PADataPatientsResponse);
            }

            if (doDataPatientsRequest.getPatients() == null){
                PADataPatientsResponse.setResultRequestCode(400);
                PADataPatientsResponseService.add(PADataPatientsResponse);
                return ResponseEntity.ok(PADataPatientsResponse);
            }
            try {
                PADataPatientsResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                PADataPatientsResponse.setDateBeg(Date.from(Instant.now()));
                PADataPatientsResponse.setDateEdit(Date.from(Instant.now()));
                PADataPatientsResponse.setReqID(doDataPatientsRequest.getReqID());
                PADataPatientsResponseService.add(PADataPatientsResponse);

                doDataPatientsRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                doDataPatientsRequest.setDateBeg(Date.from(Instant.now()));
                doDataPatientsRequest.setDateEdit(Date.from(Instant.now()));
                paDataPatientsRequestService.add(doDataPatientsRequest);

                return ResponseEntity.ok(PADataPatientsResponseService
                        .processing(doDataPatientsRequest, PADataPatientsResponse, tokenService.getCodeMOWithToken(token)));

            } catch (Exception e){
                PADataPatientsResponse.setResultRequestCode(400);
                PADataPatientsResponseService.add(PADataPatientsResponse);
            }

        } else {
            PADataPatientsResponse.setResultRequestCode(403);
            PADataPatientsResponseService.add(PADataPatientsResponse);
        }
        return ResponseEntity.ok(PADataPatientsResponse);
    }

    @PostMapping("/api/test/getDODataPatients")
    public ResponseEntity<PADataPatientsResponse> registerChoosingMOTest(
            @RequestHeader(name = "token") String token,
            @RequestBody PADataPatientsRequest doDataPatientsRequest) {

        PADataPatientsResponse PADataPatientsResponse = new PADataPatientsResponse();
        PADataPatientsResponse.setResultRequestCode(201);
        PADataPatientsResponseService.add(PADataPatientsResponse);

        if (tokenService.checkToken(token) && doDataPatientsRequest.getPatients().get(0).getSurname().equals("Премудрая")) {
            PADataPatientsResponse PADataPatientsResponseFromDB =
                    PADataPatientsResponseService.getWithReqId(doDataPatientsRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (PADataPatientsResponseFromDB != null){
                PADataPatientsResponse.setResultRequestCode(402);
                PADataPatientsResponseService.add(PADataPatientsResponse);
                return ResponseEntity.ok(PADataPatientsResponse);
            }

            if (doDataPatientsRequest.getPatients() == null){
                PADataPatientsResponse.setResultRequestCode(400);
                PADataPatientsResponseService.add(PADataPatientsResponse);
                return ResponseEntity.ok(PADataPatientsResponse);
            }
            try {
                PADataPatientsResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                PADataPatientsResponse.setDateBeg(Date.from(Instant.now()));
                PADataPatientsResponse.setDateEdit(Date.from(Instant.now()));
                PADataPatientsResponse.setReqID(doDataPatientsRequest.getReqID());
                PADataPatientsResponseService.add(PADataPatientsResponse);

                doDataPatientsRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                doDataPatientsRequest.setDateBeg(Date.from(Instant.now()));
                doDataPatientsRequest.setDateEdit(Date.from(Instant.now()));
                paDataPatientsRequestService.add(doDataPatientsRequest);

                return ResponseEntity.ok(PADataPatientsResponseService
                        .processing(doDataPatientsRequest, PADataPatientsResponse, tokenService.getCodeMOWithToken(token)));

            } catch (Exception e){
                PADataPatientsResponse.setResultRequestCode(400);
                PADataPatientsResponseService.add(PADataPatientsResponse);
            }

        } else {
            PADataPatientsResponse.setResultRequestCode(403);
            PADataPatientsResponseService.add(PADataPatientsResponse);
        }
        return ResponseEntity.ok(PADataPatientsResponse);
    }

    @GetMapping("/api/1.1/getDODataPatients/{reqID}")
    public ResponseEntity<PADataPatientsResponse> getDODataPatients(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        PADataPatientsResponse PADataPatientsResponse = new PADataPatientsResponse();
        if (tokenService.checkToken(token)) {
            try {
                PADataPatientsResponse PADataPatientsResponseFromDB
                        = PADataPatientsResponseService.getWithReqId(reqID, codeMO);
                if (PADataPatientsResponseFromDB != null){
                    return ResponseEntity.ok(PADataPatientsResponseFromDB);
                } else {
                    PADataPatientsResponse.setResultRequestCode(401);
                }
            } catch (Exception e){
                PADataPatientsResponse.setResultRequestCode(400);
            }
        } else {
            PADataPatientsResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(PADataPatientsResponse);
    }
}
