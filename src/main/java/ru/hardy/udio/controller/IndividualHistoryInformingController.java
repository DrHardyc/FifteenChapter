package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
        individualHistoryInformingResponse.setReqID(individualHistoryInformingRequest.getReqID());
        individualHistoryInformingResponseService.add(individualHistoryInformingResponse);

        if (tokenService.checkToken(token)) {
            IndividualHistoryInformingResponse individualHistoryInformingResponseFromDB =
                    individualHistoryInformingResponseService.getWithReqId(individualHistoryInformingRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (individualHistoryInformingResponseFromDB != null){
                individualHistoryInformingResponse.setResultRequestCode(402);
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
                individualHistoryInformingResponse.setDateBeg(Date.from(Instant.now()));
                individualHistoryInformingResponse.setDateEdit(Date.from(Instant.now()));
                individualHistoryInformingResponse.setReqID(individualHistoryInformingRequest.getReqID());
                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);

                individualHistoryInformingRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                individualHistoryInformingRequest.setDateBeg(Date.from(Instant.now()));
                individualHistoryInformingRequest.setDateEdit(Date.from(Instant.now()));
                individualHistoryInformingRequestService.add(individualHistoryInformingRequest);

                return ResponseEntity.ok(individualHistoryInformingResponseService
                        .processing(individualHistoryInformingRequest, individualHistoryInformingResponse, tokenService.getCodeMOWithToken(token)));

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

}
