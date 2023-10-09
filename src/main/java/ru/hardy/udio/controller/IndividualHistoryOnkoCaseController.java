package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponse;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseRequestService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseResponseService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class IndividualHistoryOnkoCaseController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IndividualHistoryOnkoCaseRequestService individualHistoryOnkoCaseRequestService;

    @Autowired
    private IndividualHistoryOnkoCaseResponseService individualHistoryOnkoCaseResponseService;

    @PostMapping("/api/1.1/getIndividualHistoryOnkoCase")
    public ResponseEntity<IndividualHistoryOnkoCaseResponse> registerIndividualHistoryOnkoCase(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest) {

        IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse = new IndividualHistoryOnkoCaseResponse();
        individualHistoryOnkoCaseResponse.setResultRequestCode(201);
        individualHistoryOnkoCaseResponse.setDateBeg(Date.from(Instant.now()));
        individualHistoryOnkoCaseResponse.setDateEdit(Date.from(Instant.now()));
        individualHistoryOnkoCaseResponse.setReqID(individualHistoryOnkoCaseRequest.getReqID());
        individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);

        if (tokenService.checkToken(token)) {
            IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponseFromDB =
                    individualHistoryOnkoCaseResponseService.getWithReqId(individualHistoryOnkoCaseRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (individualHistoryOnkoCaseResponseFromDB != null){
                individualHistoryOnkoCaseResponse.setResultRequestCode(402);
                individualHistoryOnkoCaseResponse.setDateBeg(Date.from(Instant.now()));
                individualHistoryOnkoCaseResponse.setDateEdit(Date.from(Instant.now()));
                individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);
                return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
            }

            if (individualHistoryOnkoCaseRequest.getPatients() == null){
                individualHistoryOnkoCaseResponse.setResultRequestCode(400);
                individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);
                return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
            }
            try {
                individualHistoryOnkoCaseResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);

                individualHistoryOnkoCaseRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                individualHistoryOnkoCaseRequest.setDateBeg(Date.from(Instant.now()));
                individualHistoryOnkoCaseRequest.setDateEdit(Date.from(Instant.now()));
                individualHistoryOnkoCaseRequestService.add(individualHistoryOnkoCaseRequest);

                return ResponseEntity.ok(individualHistoryOnkoCaseResponseService
                        .processing(individualHistoryOnkoCaseRequest, individualHistoryOnkoCaseResponse));

            } catch (Exception e){
                individualHistoryOnkoCaseResponse.setResultRequestCode(400);
                individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);
            }

        } else {
            individualHistoryOnkoCaseResponse.setResultRequestCode(403);
            individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);
        }
        return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
    }


    @PostMapping("/api/test/getIndividualHistoryOnkoCase")
    public ResponseEntity<IndividualHistoryOnkoCaseResponse> registerIndividualHistoryOnkoCaseTest(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest) {

        IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse = new IndividualHistoryOnkoCaseResponse();

        if (tokenService.checkToken(token)) {
            if (individualHistoryOnkoCaseRequest.getPatients().get(0).getSurname().equals("Премудрая")) {
                IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponseFromDB =
                        individualHistoryOnkoCaseResponseService.getWithReqId(individualHistoryOnkoCaseRequest.getReqID(),
                                tokenService.getCodeMOWithToken(token));
                if (individualHistoryOnkoCaseResponseFromDB != null) {
                    individualHistoryOnkoCaseResponse.setResultRequestCode(402);
                    individualHistoryOnkoCaseResponse.setDateBeg(Date.from(Instant.now()));
                    individualHistoryOnkoCaseResponse.setDateEdit(Date.from(Instant.now()));
                    individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);
                    return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
                }

                if (individualHistoryOnkoCaseRequest.getPatients() == null) {
                    individualHistoryOnkoCaseResponse.setResultRequestCode(400);
                    individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);
                    return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
                }
                try {
                    individualHistoryOnkoCaseResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                    individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);

                    individualHistoryOnkoCaseRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                    individualHistoryOnkoCaseRequest.setDateBeg(Date.from(Instant.now()));
                    individualHistoryOnkoCaseRequest.setDateEdit(Date.from(Instant.now()));
                    individualHistoryOnkoCaseRequestService.add(individualHistoryOnkoCaseRequest);

                    return ResponseEntity.ok(individualHistoryOnkoCaseResponseService
                            .processing(individualHistoryOnkoCaseRequest, individualHistoryOnkoCaseResponse));

                } catch (Exception e) {
                    individualHistoryOnkoCaseResponse.setResultRequestCode(400);
                    individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);
                }

            } else {
                individualHistoryOnkoCaseResponse.setResultRequestCode(403);
                individualHistoryOnkoCaseResponseService.add(individualHistoryOnkoCaseResponse);
            }
        }
        return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
    }
}
