package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.individualhistoryinformingresponseservice.IndividualHistoryInformingRequestService;
import ru.hardy.udio.service.apiservice.individualhistoryinformingresponseservice.IndividualHistoryInformingResponseService;

import java.time.Instant;
import java.util.Date;

@Controller
public class IndividualHistoryInformingController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IndividualHistoryInformingResponseService individualHistoryInformingResponseService;

    @Autowired
    private IndividualHistoryInformingRequestService individualHistoryInformingRequestService;

    @PostMapping("/api/1.1/getIndividualHistoryInforming")
    public ResponseEntity<IndividualHistoryInformingResponse> registerIndividualHistoryInforming(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryInformingRequest individualHistoryInformingRequest) {
        IndividualHistoryInformingResponse individualHistoryInformingResponse = new IndividualHistoryInformingResponse();
        individualHistoryInformingResponse.setResultRequestCode(201);
        individualHistoryInformingResponse.setDateBeg(Date.from(Instant.now()));
        individualHistoryInformingResponse.setDateEdit(Date.from(Instant.now()));
        individualHistoryInformingResponse.setReqID(individualHistoryInformingRequest.getReqID());
        individualHistoryInformingResponseService.add(individualHistoryInformingResponse);

        if (tokenService.checkToken(token)) {
            IndividualHistoryInformingResponse individualHistoryInformingResponseFromDB =
                    individualHistoryInformingResponseService.getWithReqId(individualHistoryInformingRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (individualHistoryInformingResponseFromDB != null){
                individualHistoryInformingResponse.setResultRequestCode(402);
                individualHistoryInformingResponse.setDateBeg(Date.from(Instant.now()));
                individualHistoryInformingResponse.setDateEdit(Date.from(Instant.now()));
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
                return ResponseEntity.ok(individualHistoryInformingResponse);
            }

            if (individualHistoryInformingRequest.getPatients() == null){
                individualHistoryInformingResponse.setResultRequestCode(400);
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
                return ResponseEntity.ok(individualHistoryInformingResponse);
            }
            try {
                individualHistoryInformingResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);

                individualHistoryInformingRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                individualHistoryInformingRequest.setDateBeg(Date.from(Instant.now()));
                individualHistoryInformingRequest.setDateEdit(Date.from(Instant.now()));
                individualHistoryInformingRequestService.add(individualHistoryInformingRequest);

                return ResponseEntity.ok(individualHistoryInformingResponseService
                        .processing(individualHistoryInformingRequest, individualHistoryInformingResponse));

            } catch (Exception e){
                individualHistoryInformingResponse.setResultRequestCode(400);
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
            }

        } else {
            individualHistoryInformingResponse.setResultRequestCode(403);
            individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
        }
        return ResponseEntity.ok(individualHistoryInformingResponse);
    }

    @PostMapping("/api/test/getIndividualHistoryInforming")
    public ResponseEntity<IndividualHistoryInformingResponse> registerIndividualHistoryInformingTest(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryInformingRequest individualHistoryInformingRequest) {
        IndividualHistoryInformingResponse individualHistoryInformingResponse = new IndividualHistoryInformingResponse();
        individualHistoryInformingResponse.setResultRequestCode(201);
        individualHistoryInformingResponse.setDateBeg(Date.from(Instant.now()));
        individualHistoryInformingResponse.setDateEdit(Date.from(Instant.now()));
        individualHistoryInformingResponse.setReqID(individualHistoryInformingRequest.getReqID());
        individualHistoryInformingResponseService.add(individualHistoryInformingResponse);

        if (tokenService.checkToken(token) && individualHistoryInformingRequest.getPatients().get(0).getSurname().equals("Премудрая")) {
            IndividualHistoryInformingResponse individualHistoryInformingResponseFromDB =
                    individualHistoryInformingResponseService.getWithReqId(individualHistoryInformingRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (individualHistoryInformingResponseFromDB != null){
                individualHistoryInformingResponse.setResultRequestCode(402);
                individualHistoryInformingResponse.setDateBeg(Date.from(Instant.now()));
                individualHistoryInformingResponse.setDateEdit(Date.from(Instant.now()));
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
                return ResponseEntity.ok(individualHistoryInformingResponse);
            }

            if (individualHistoryInformingRequest.getPatients() == null){
                individualHistoryInformingResponse.setResultRequestCode(400);
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
                return ResponseEntity.ok(individualHistoryInformingResponse);
            }
            try {
                individualHistoryInformingResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);

                individualHistoryInformingRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                individualHistoryInformingRequest.setDateBeg(Date.from(Instant.now()));
                individualHistoryInformingRequest.setDateEdit(Date.from(Instant.now()));
                individualHistoryInformingRequestService.add(individualHistoryInformingRequest);

                return ResponseEntity.ok(individualHistoryInformingResponseService
                        .processing(individualHistoryInformingRequest, individualHistoryInformingResponse));

            } catch (Exception e){
                individualHistoryInformingResponse.setResultRequestCode(400);
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
            }

        } else {
            individualHistoryInformingResponse.setResultRequestCode(403);
            individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
        }
        return ResponseEntity.ok(individualHistoryInformingResponse);
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
                    individualHistoryInformingResponse.setResultRequestCode(401);
                }
            } catch (Exception e){
                individualHistoryInformingResponse.setResultRequestCode(400);
            }
        } else {
            individualHistoryInformingResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(individualHistoryInformingResponse);
    }

}
