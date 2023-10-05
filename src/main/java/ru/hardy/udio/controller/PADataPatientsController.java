package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequest;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientRequestService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientResponseService;

import java.time.Instant;
import java.util.Date;

@RestController
public class PADataPatientsController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PADataPatientResponseService PADataPatientResponseService;

    @Autowired
    private PADataPatientRequestService paDataPatientRequestService;

    @PostMapping("/api/1.1/getPADataPatients")
    public ResponseEntity<PADataPatientResponse> registerChoosingMO(
            @RequestHeader(name = "token") String token,
            @RequestBody PADataPatientRequest doDataPatientsRequest) {

        PADataPatientResponse PADataPatientResponse = new PADataPatientResponse();
        PADataPatientResponse.setResultRequestCode(201);
        PADataPatientResponseService.add(PADataPatientResponse);

        if (tokenService.checkToken(token)) {
            PADataPatientResponse PADataPatientResponseFromDB =
                    PADataPatientResponseService.getWithReqId(doDataPatientsRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (PADataPatientResponseFromDB != null){
                PADataPatientResponse.setResultRequestCode(402);
                PADataPatientResponseService.add(PADataPatientResponse);
                return ResponseEntity.ok(PADataPatientResponse);
            }

            if (doDataPatientsRequest.getPatients() == null){
                PADataPatientResponse.setResultRequestCode(400);
                PADataPatientResponseService.add(PADataPatientResponse);
                return ResponseEntity.ok(PADataPatientResponse);
            }
            try {
                PADataPatientResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                PADataPatientResponse.setDateBeg(Date.from(Instant.now()));
                PADataPatientResponse.setDateEdit(Date.from(Instant.now()));
                PADataPatientResponse.setReqID(doDataPatientsRequest.getReqID());
                PADataPatientResponseService.add(PADataPatientResponse);

                doDataPatientsRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                doDataPatientsRequest.setDateBeg(Date.from(Instant.now()));
                doDataPatientsRequest.setDateEdit(Date.from(Instant.now()));
                paDataPatientRequestService.add(doDataPatientsRequest);

                return ResponseEntity.ok(PADataPatientResponseService
                        .processing(doDataPatientsRequest, PADataPatientResponse, tokenService.getCodeMOWithToken(token)));

            } catch (Exception e){
                PADataPatientResponse.setResultRequestCode(400);
                PADataPatientResponseService.add(PADataPatientResponse);
            }

        } else {
            PADataPatientResponse.setResultRequestCode(403);
            PADataPatientResponseService.add(PADataPatientResponse);
        }
        return ResponseEntity.ok(PADataPatientResponse);
    }

    @PostMapping("/api/test/getDODataPatients")
    public ResponseEntity<PADataPatientResponse> registerChoosingMOTest(
            @RequestHeader(name = "token") String token,
            @RequestBody PADataPatientRequest doDataPatientsRequest) {

        PADataPatientResponse PADataPatientResponse = new PADataPatientResponse();
        PADataPatientResponse.setResultRequestCode(201);
        PADataPatientResponseService.add(PADataPatientResponse);

        if (tokenService.checkToken(token) && doDataPatientsRequest.getPatients().get(0).getSurname().equals("Премудрая")) {
            PADataPatientResponse PADataPatientResponseFromDB =
                    PADataPatientResponseService.getWithReqId(doDataPatientsRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (PADataPatientResponseFromDB != null){
                PADataPatientResponse.setResultRequestCode(402);
                PADataPatientResponseService.add(PADataPatientResponse);
                return ResponseEntity.ok(PADataPatientResponse);
            }

            if (doDataPatientsRequest.getPatients() == null){
                PADataPatientResponse.setResultRequestCode(400);
                PADataPatientResponseService.add(PADataPatientResponse);
                return ResponseEntity.ok(PADataPatientResponse);
            }
            try {
                PADataPatientResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                PADataPatientResponse.setDateBeg(Date.from(Instant.now()));
                PADataPatientResponse.setDateEdit(Date.from(Instant.now()));
                PADataPatientResponse.setReqID(doDataPatientsRequest.getReqID());
                PADataPatientResponseService.add(PADataPatientResponse);

                doDataPatientsRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                doDataPatientsRequest.setDateBeg(Date.from(Instant.now()));
                doDataPatientsRequest.setDateEdit(Date.from(Instant.now()));
                paDataPatientRequestService.add(doDataPatientsRequest);

                return ResponseEntity.ok(PADataPatientResponseService
                        .processing(doDataPatientsRequest, PADataPatientResponse, tokenService.getCodeMOWithToken(token)));

            } catch (Exception e){
                PADataPatientResponse.setResultRequestCode(400);
                PADataPatientResponseService.add(PADataPatientResponse);
            }

        } else {
            PADataPatientResponse.setResultRequestCode(403);
            PADataPatientResponseService.add(PADataPatientResponse);
        }
        return ResponseEntity.ok(PADataPatientResponse);
    }

    @GetMapping("/api/1.1/getDODataPatients/{reqID}")
    public ResponseEntity<PADataPatientResponse> getDODataPatients(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        PADataPatientResponse PADataPatientResponse = new PADataPatientResponse();
        if (tokenService.checkToken(token)) {
            try {
                PADataPatientResponse PADataPatientResponseFromDB
                        = PADataPatientResponseService.getWithReqId(reqID, codeMO);
                if (PADataPatientResponseFromDB != null){
                    return ResponseEntity.ok(PADataPatientResponseFromDB);
                } else {
                    PADataPatientResponse.setResultRequestCode(401);
                }
            } catch (Exception e){
                PADataPatientResponse.setResultRequestCode(400);
            }
        } else {
            PADataPatientResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(PADataPatientResponse);
    }
}
