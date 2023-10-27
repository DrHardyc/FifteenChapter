package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequest;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.APIRequestService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientResponseService;

@RestController
public class PADataPatientsController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private PADataPatientResponseService paDataPatientResponseService;
    @Autowired
    private APIRequestService apiRequestService;

    @PostMapping("/api/1.1/getPADataPatient")
    public ResponseEntity<PADataPatientResponse> registerPADataPatient(
            @RequestHeader(name = "token") String token,
            @RequestBody PADataPatientRequest paDataPatientRequest) {

        return ResponseEntity
                .ok((PADataPatientResponse) apiRequestService
                        .acceptance(token, paDataPatientRequest));
    }

    @PostMapping("/api/test/getPADataPatient")
    public ResponseEntity<PADataPatientResponse> registerPADataPatientTest(
            @RequestHeader(name = "token") String token,
            @RequestBody PADataPatientRequest paDataPatientRequest) {

        return ResponseEntity.ok((PADataPatientResponse) apiRequestService.acceptance(token, paDataPatientRequest));    }

    @GetMapping("/api/1.1/getPADataPatients/{reqID}")
    public ResponseEntity<PADataPatientResponse> getPADataPatients(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        PADataPatientResponse PADataPatientResponse = new PADataPatientResponse();
        if (tokenService.checkToken(token)) {
            try {
                PADataPatientResponse PADataPatientResponseFromDB
                        = paDataPatientResponseService.getWithReqId(reqID, codeMO);
                if (PADataPatientResponseFromDB != null){
                    return ResponseEntity.ok(PADataPatientResponseFromDB);
                } else {
                    PADataPatientResponse.setResultResponseCode(401);
                }
            } catch (Exception e){
                PADataPatientResponse.setResultResponseCode(400);
            }
        } else {
            PADataPatientResponse.setResultResponseCode(403);
        }
        return ResponseEntity.ok(PADataPatientResponse);
    }
}
