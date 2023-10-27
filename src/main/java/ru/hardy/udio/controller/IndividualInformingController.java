package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.APIRequestService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingResponseService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndividualInformingController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IndividualInformingResponseService individualInformingResponseService;

    @Autowired
    private APIRequestService apiRequestService;
    @PostMapping("/api/1.1/getIndividualInforming")
    public ResponseEntity<IndividualInformingResponse> registerIndividualInforming(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualInformingRequest individualInformingRequest) {
        return ResponseEntity
                .ok((IndividualInformingResponse) apiRequestService
                        .acceptance(token, individualInformingRequest));
    }

    @PostMapping("/api/test/getIndividualInforming")
    public ResponseEntity<IndividualInformingResponse> registerIndividualInformingTest(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualInformingRequest individualInformingRequest) {

        IndividualInformingResponse individualInformingResponse = new IndividualInformingResponse();
        List<IndividualInformingResponseRecord> individualInformingResponseRecords = new ArrayList<>();
        individualInformingResponse.setResultResponseCode(200);
        individualInformingResponse.setReqID(individualInformingRequest.getReqID());
        individualInformingResponse.setNumberRecordsProcessed(1);
        if (token.equals("e/SGvhPZm?usABQ9RT-gf9lyeVvYpztQG779xYQZhPyaDZolE=QNldo3ka/chYxrV4Z4mhBMCwtOLouOXihizs0XLEA0RVcLaUmI79L6ZetOl7x8=dDi4ntQ?WRMbI/?")) {
            for (IndividualInformingRequestRecord individualInformingRequestRecord : individualInformingRequest.getPatients()){
                if (individualInformingRequestRecord.getSurname().equals("Премудрая") &&
                        individualInformingRequestRecord.getName().equals("Василиса")  &&
                        individualInformingRequestRecord.getPatronymic().equals("Ивановна")) {
                    individualInformingResponseRecords.add(new IndividualInformingResponseRecord(individualInformingRequestRecord,
                            individualInformingResponse, 500, "Успешное выполнение обработки записи"));
                } else {
                    individualInformingResponseRecords.add(new IndividualInformingResponseRecord(individualInformingRequestRecord,
                            individualInformingResponse, 503, "Ошибка поиска в СРЗ"));
                }

            }
            individualInformingResponse.setPatients(individualInformingResponseRecords);

        } else {
            individualInformingResponse.setResultResponseCode(403);
            individualInformingResponseService.add(individualInformingResponse);
        }
        return ResponseEntity.ok(individualInformingResponse);
    }

    @GetMapping("/api/1.1/getIndividualInforming/{reqID}")
    public ResponseEntity<IndividualInformingResponse> getIndividualInforming(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        IndividualInformingResponse individualInformingResponse = new IndividualInformingResponse();
        if (tokenService.checkToken(token)) {
            try {
                IndividualInformingResponse individualInformingResponseFromDB = individualInformingResponseService.getWithReqId(reqID, codeMO);
                if (individualInformingResponseFromDB != null){
                    return ResponseEntity.ok(individualInformingResponseFromDB);
                } else {
                    individualInformingResponse.setResultResponseCode(401);
                }
            } catch (Exception e){
                individualInformingResponse.setResultResponseCode(400);
            }
        } else {
            individualInformingResponse.setResultResponseCode(403);
        }
        return ResponseEntity.ok(individualInformingResponse);
    }
}
