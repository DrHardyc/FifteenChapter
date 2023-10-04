package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponse;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponseEntity;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseRequestService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice.IndividualHistoryOnkoCaseResponseEntityService;
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
    private IndividualHistoryOnkoCaseResponseEntityService individualHistoryOnkoCaseResponseEntityService;

    @Autowired
    private IndividualHistoryOnkoCaseResponseService individualHistoryOnkoCaseResponseService;

    @PostMapping("/api/1.1/getIndividualHistoryOnkoCase")
    public ResponseEntity<IndividualHistoryOnkoCaseResponse> registerIndividualHistoryOnkoCase(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest) {

        IndividualHistoryOnkoCaseResponseEntity individualHistoryOnkoCaseResponseEntity = new IndividualHistoryOnkoCaseResponseEntity();
        IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse = new IndividualHistoryOnkoCaseResponse();

        if (tokenService.checkToken(token)) {
            try{
                individualHistoryOnkoCaseRequest.setDateBeg(Date.from(Instant.now()));
                individualHistoryOnkoCaseRequest.setDateEdit(Date.from(Instant.now()));
                individualHistoryOnkoCaseRequestService.add(individualHistoryOnkoCaseRequest);
                individualHistoryOnkoCaseResponseEntity.setPatientRequest(individualHistoryOnkoCaseRequest);
                individualHistoryOnkoCaseResponseEntity.setResultRequestCode(200);
                individualHistoryOnkoCaseResponseEntityService.add(individualHistoryOnkoCaseResponseEntity);
                return ResponseEntity.ok(individualHistoryOnkoCaseResponseService
                        .processind(individualHistoryOnkoCaseRequest, individualHistoryOnkoCaseResponseEntity));
            } catch (Exception e){
                individualHistoryOnkoCaseResponseEntity.setResultRequestCode(400);
                individualHistoryOnkoCaseResponseEntityService.add(individualHistoryOnkoCaseResponseEntity);
                individualHistoryOnkoCaseResponse.setResultRequestCode(400);
            }
        } else {
            individualHistoryOnkoCaseResponseEntity.setResultRequestCode(403);
            individualHistoryOnkoCaseResponseEntityService.add(individualHistoryOnkoCaseResponseEntity);
            individualHistoryOnkoCaseResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
    }


    @PostMapping("/api/test/getIndividualHistoryOnkoCase")
    public ResponseEntity<IndividualHistoryOnkoCaseResponse> registerIndividualHistoryOnkoCaseTest(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest) {

        IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse = new IndividualHistoryOnkoCaseResponse();

        if (tokenService.checkToken(token)) {
            try{
                if (individualHistoryOnkoCaseRequest.getSurname().equals("Премудрая")){
                    List<IndividualHistoryOnkoCaseResponseRecord> individualHistoryOnkoCaseResponseRecords = new ArrayList<>();
                    individualHistoryOnkoCaseResponseRecords.add(new IndividualHistoryOnkoCaseResponseRecord(
                            -1, -1, "9284886699", "H-999", Date.from(Instant.now()),
                            "Посещение", Date.from(Instant.now()), Date.from(Instant.now()), "C20",
                            "I25.2", null, "Выписан", null
                    ));
                    individualHistoryOnkoCaseResponseRecords.add(new IndividualHistoryOnkoCaseResponseRecord(
                            -1, -1, "9284886699", "H-998", Date.from(Instant.now()),
                            "Посещение", Date.from(Instant.now()), Date.from(Instant.now()), "C20",
                            null, null, "Лечение продолжено", null
                    ));
                    individualHistoryOnkoCaseResponse.setSurname(individualHistoryOnkoCaseRequest.getSurname());
                    individualHistoryOnkoCaseResponse.setName(individualHistoryOnkoCaseRequest.getName());
                    individualHistoryOnkoCaseResponse.setPatronymic(individualHistoryOnkoCaseRequest.getPatronymic());
                    individualHistoryOnkoCaseResponse.setDateBirth(individualHistoryOnkoCaseRequest.getDateBirth());
                    individualHistoryOnkoCaseResponse.setEnp(individualHistoryOnkoCaseRequest.getEnp());
                    individualHistoryOnkoCaseResponse.setInsuranceCase(individualHistoryOnkoCaseResponseRecords);
                    individualHistoryOnkoCaseResponse.setResultRequestCode(200);
                    return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
                }
            } catch (Exception e){
                individualHistoryOnkoCaseResponse.setResultRequestCode(400);
            }
        } else {
            individualHistoryOnkoCaseResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
    }
}
