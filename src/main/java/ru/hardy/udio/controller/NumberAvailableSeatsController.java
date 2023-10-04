package ru.hardy.udio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponse;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.NumberAvailableSeatsRequestService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.NumberAvailableSeatsResponseService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        int codeMO = tokenService.getCodeMOWithToken(token);
        NumberAvailableSeatsResponse numberAvailableSeatsResponse = new NumberAvailableSeatsResponse();

        if (tokenService.checkToken(token)) {
            if (numberAvailableSeatsResponseService.getWithReqId(numberAvailableSeatsRequest.getReqID(), codeMO) != null){
                numberAvailableSeatsResponse.setResultRequestCode(402);
                numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);
                return ResponseEntity.ok(numberAvailableSeatsResponse);
            }

            numberAvailableSeatsResponse.setResultRequestCode(201);
            numberAvailableSeatsResponse.setDate_beg(Date.from(Instant.now()));
            numberAvailableSeatsResponse.setDate_edit(Date.from(Instant.now()));
            numberAvailableSeatsResponse.setReqID(numberAvailableSeatsRequest.getReqID());
            numberAvailableSeatsResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
            numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);

            if (numberAvailableSeatsRequest.getDepartments() == null){
                numberAvailableSeatsResponse.setResultRequestCode(400);
                numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);
                return ResponseEntity.ok(numberAvailableSeatsResponse);
            }

            try {
                numberAvailableSeatsRequest.setCodeMO(codeMO);
                numberAvailableSeatsRequest.setDate_beg(Date.from(Instant.now()));
                numberAvailableSeatsRequest.setDate_edit(Date.from(Instant.now()));
                numberAvailableSeatsRequestService.add(numberAvailableSeatsRequest);

                numberAvailableSeatsResponse.setResultRequestCode(200);
                numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);

                return ResponseEntity.ok(numberAvailableSeatsResponseService
                        .processing(numberAvailableSeatsRequest, numberAvailableSeatsResponse, codeMO));

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

    @PostMapping("/api/test/getNumberAvailableSeats")
    public ResponseEntity<NumberAvailableSeatsResponse> registerNumberAvailableSeatsTest(
            @RequestHeader(name = "token") String token,
            @RequestBody NumberAvailableSeatsRequest numberAvailableSeatsRequest) {

        NumberAvailableSeatsResponse numberAvailableSeatsResponse = new NumberAvailableSeatsResponse();
        List<NumberAvailableSeatsResponseRecord> numberAvailableSeatsResponseRecords = new ArrayList<>();
        if (tokenService.checkToken(token)) {

            try {
                for (NumberAvailableSeatsRequestRecord numberAvailableSeatsRequestRecord : numberAvailableSeatsRequest.getDepartments()){
                    numberAvailableSeatsResponseRecords.add(new NumberAvailableSeatsResponseRecord(
                            numberAvailableSeatsRequestRecord, numberAvailableSeatsResponse,
                            500, "Успешное выполнение обработки записи"));
                }
                numberAvailableSeatsResponse.setDepartmentResponse(numberAvailableSeatsResponseRecords);
                numberAvailableSeatsRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                numberAvailableSeatsRequest.setDate_beg(Date.from(Instant.now()));
                numberAvailableSeatsRequest.setDate_edit(Date.from(Instant.now()));

                numberAvailableSeatsResponse.setReqID(numberAvailableSeatsRequest.getReqID());
                numberAvailableSeatsResponse.setNumberRecordsProcessed(1);
                numberAvailableSeatsResponse.setResultRequestCode(200);

                return ResponseEntity.ok(numberAvailableSeatsResponse);

            } catch (Exception e){
                numberAvailableSeatsResponse.setResultRequestCode(400);
            }

        } else {
            numberAvailableSeatsResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(numberAvailableSeatsResponse);
    }

    @GetMapping("/api/1.1/getNumberAvailableSeats/{reqID}")
    public ResponseEntity<NumberAvailableSeatsResponse> getNumberAvailableSeats(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        NumberAvailableSeatsResponse numberAvailableSeatsResponse = new NumberAvailableSeatsResponse();
        if (tokenService.checkToken(token)) {
            try {
                NumberAvailableSeatsResponse numberAvailableSeatsResponseFromDB = numberAvailableSeatsResponseService.getWithReqId(reqID, codeMO);
                if (numberAvailableSeatsResponseFromDB != null){
                    return ResponseEntity.ok(numberAvailableSeatsResponseFromDB);
                } else {
                    numberAvailableSeatsResponse.setResultRequestCode(401);
                }
            } catch (Exception e){
                numberAvailableSeatsResponse.setResultRequestCode(400);
            }
        } else {
            numberAvailableSeatsResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(numberAvailableSeatsResponse);
    }

}
