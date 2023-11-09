package ru.hardy.udio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsRequest;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsResponse;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsResponseRecord;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMORequest;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMOResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.APIRequestService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.mo.NumberAvailableSeatsResponseService;
import ru.hardy.udio.service.nsiservice.MedicalOrganizationService;

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
    private APIRequestService apiRequestService;

    @Autowired
    private MedicalOrganizationService medicalOrganizationService;

    @PostMapping("/api/1.1/setNumberAvailableSeats")
    public ResponseEntity<NumberAvailableSeatsResponse> registerNumberAvailableSeats(
            @RequestHeader(name = "token") String token,
            @RequestBody NumberAvailableSeatsRequest numberAvailableSeatsRequest) {

        return ResponseEntity
                .ok((NumberAvailableSeatsResponse) apiRequestService
                        .acceptance(token, numberAvailableSeatsRequest));
    }

    @PostMapping("/api/1.1/getNumberAvailableSeats")
    public ResponseEntity<NumberAvailableSeatsSMOResponse> getNumberAvailableSeats(
            @RequestHeader(name = "token") String token,
            @RequestBody NumberAvailableSeatsSMORequest numberAvailableSeatsSMORequest) {

        return ResponseEntity
                .ok((NumberAvailableSeatsSMOResponse) apiRequestService
                        .acceptance(token, numberAvailableSeatsSMORequest));
    }

    @PostMapping("/api/test/setNumberAvailableSeats")
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
                numberAvailableSeatsRequest.setMedicalOrganization(medicalOrganizationService.getByCode(tokenService.getCodeMOWithToken(token)));
                numberAvailableSeatsRequest.setDateBeg(Date.from(Instant.now()));
                numberAvailableSeatsRequest.setDateEdit(Date.from(Instant.now()));

                numberAvailableSeatsResponse.setReqID(numberAvailableSeatsRequest.getReqID());
                numberAvailableSeatsResponse.setNumberRecordsProcessed(1);
                numberAvailableSeatsResponse.setResultResponseCode(200);

                return ResponseEntity.ok(numberAvailableSeatsResponse);

            } catch (Exception e){
                numberAvailableSeatsResponse.setResultResponseCode(400);
            }

        } else {
            numberAvailableSeatsResponse.setResultResponseCode(403);
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
                    numberAvailableSeatsResponse.setResultResponseCode(401);
                }
            } catch (Exception e){
                numberAvailableSeatsResponse.setResultResponseCode(400);
            }
        } else {
            numberAvailableSeatsResponse.setResultResponseCode(403);
        }
        return ResponseEntity.ok(numberAvailableSeatsResponse);
    }

}
