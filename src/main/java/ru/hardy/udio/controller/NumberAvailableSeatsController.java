package ru.hardy.udio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.NumberAvailableSeatsRequestRecordService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.NumberAvailableSeatsRequestService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.NumberAvailableSeatsResponseService;

import java.time.Instant;
import java.util.Date;

@RestController
public class NumberAvailableSeatsController {

    @Autowired
    private NumberAvailableSeatsResponseService numberAvailableSeatsResponseService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private NumberAvailableSeatsRequestService numberAvailableSeatsRequestService;

    @PostMapping("/api/1.1/getNumberAvailableSeats")
    public ResponseEntity<NumberAvailableSeatsResponse> registerNumberAvailableSeats(
            @RequestHeader(name = "token") String token,
            @RequestBody NumberAvailableSeatsRequest numberAvailableSeatsRequest) {

        NumberAvailableSeatsResponse numberAvailableSeatsResponse = new NumberAvailableSeatsResponse();

        if (tokenService.checkToken(token)) {
            numberAvailableSeatsResponse.setResultRequestCode(201);
            numberAvailableSeatsResponse.setDate_beg(Date.from(Instant.now()));
            numberAvailableSeatsResponse.setDate_edit(Date.from(Instant.now()));
            numberAvailableSeatsResponse.setReqID(numberAvailableSeatsRequest.getReqID());
            numberAvailableSeatsResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
            numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);

            if (numberAvailableSeatsRequestService.getWithReqId(numberAvailableSeatsRequest.getReqID(), token).size() > 0){
                numberAvailableSeatsResponse.setResultRequestCode(402);
                numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);
                return ResponseEntity.ok(numberAvailableSeatsResponse);
            }
            if (numberAvailableSeatsRequest.getDepartments() == null){
                numberAvailableSeatsResponse.setResultRequestCode(400);
                numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);
                return ResponseEntity.ok(numberAvailableSeatsResponse);
            }

            try {
                numberAvailableSeatsRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                numberAvailableSeatsRequest.setDate_beg(Date.from(Instant.now()));
                numberAvailableSeatsRequest.setDate_edit(Date.from(Instant.now()));
                numberAvailableSeatsRequestService.add(numberAvailableSeatsRequest);

                numberAvailableSeatsResponse.setResultRequestCode(200);
                numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);

                return ResponseEntity.ok(numberAvailableSeatsResponseService
                        .processing(numberAvailableSeatsRequest, numberAvailableSeatsResponse, token));

            } catch (Exception e){
                numberAvailableSeatsResponse.setResultRequestCode(400);
                numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);
            }

        } else {
            numberAvailableSeatsResponse.setResultRequestCode(403);
            numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);
        }
        return ResponseEntity.ok(numberAvailableSeatsResponse);
    }

}
