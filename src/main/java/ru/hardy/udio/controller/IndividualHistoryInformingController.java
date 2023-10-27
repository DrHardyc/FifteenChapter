package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.APIRequestService;
import ru.hardy.udio.service.apiservice.individualhistoryinformingservice.IndividualHistoryInformingResponseService;

@Controller
public class IndividualHistoryInformingController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IndividualHistoryInformingResponseService individualHistoryInformingResponseService;

    @Autowired
    private APIRequestService apiRequestService;

    @PostMapping("/api/1.1/getIndividualHistoryInforming")
    public ResponseEntity<IndividualHistoryInformingResponse> registerIndividualHistoryInforming(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryInformingRequest individualHistoryInformingRequest) {
        return ResponseEntity
                .ok((IndividualHistoryInformingResponse) apiRequestService
                        .acceptance(token, individualHistoryInformingRequest));
    }

    @PostMapping("/api/test/getIndividualHistoryInforming")
    public ResponseEntity<IndividualHistoryInformingResponse> registerIndividualHistoryInformingTest(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryInformingRequest individualHistoryInformingRequest) {
        return ResponseEntity.ok((IndividualHistoryInformingResponse) apiRequestService.acceptance(token, individualHistoryInformingRequest));
    }

    @GetMapping("/api/1.1/getIndividualHistoryInforming/{reqID}")
    public ResponseEntity<IndividualHistoryInformingResponse> getIndividualInforming(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        IndividualHistoryInformingResponse individualHistoryInformingResponse = new IndividualHistoryInformingResponse();
        if (tokenService.checkToken(token)) {
            try {
                IndividualHistoryInformingResponse individualHistoryInformingResponseFromDB = individualHistoryInformingResponseService.getWithReqId(reqID, codeMO);
                if (individualHistoryInformingResponseFromDB != null){
                    return ResponseEntity.ok(individualHistoryInformingResponseFromDB);
                } else {
                    individualHistoryInformingResponse.setResultResponseCode(401);
                }
            } catch (Exception e){
                individualHistoryInformingResponse.setResultResponseCode(400);
            }
        } else {
            individualHistoryInformingResponse.setResultResponseCode(403);
        }
        return ResponseEntity.ok(individualHistoryInformingResponse);
    }

}
