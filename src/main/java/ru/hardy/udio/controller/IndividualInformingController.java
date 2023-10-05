package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponseRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.individualhistoryinformingresponseservice.IndividualHistoryInformingService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestRecordService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingResponseService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndividualInformingController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IndividualInformingResponseService individualInformingResponseService;

    @Autowired
    private IndividualInformingRequestService individualInformingRequestService;

    @Autowired
    private IndividualHistoryInformingService individualHistoryInformingService;

    @Autowired
    private PeopleService peopleService;

    @PostMapping("/api/1.1/getIndividualInforming")
    public ResponseEntity<IndividualInformingResponse> registerIndividualInforming(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualInformingRequest individualInformingRequest) {

        int codeMO = tokenService.getCodeMOWithToken(token);
        IndividualInformingResponse individualInformingResponse = new IndividualInformingResponse();

        if (tokenService.checkToken(token)) {
            if (individualInformingResponseService.getWithReqId(individualInformingRequest.getReqID(), codeMO) != null){
                individualInformingResponse.setResultRequestCode(402);
                individualInformingResponseService.add(individualInformingResponse);
                return ResponseEntity.ok(individualInformingResponse);
            }

            individualInformingResponse.setResultRequestCode(201);
            individualInformingResponse.setDateBeg(Date.from(Instant.now()));
            individualInformingResponse.setDateEdit(Date.from(Instant.now()));
            individualInformingResponse.setReqID(individualInformingRequest.getReqID());
            individualInformingResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
            individualInformingResponseService.add(individualInformingResponse);

            if (individualInformingRequest.getPatients() == null){
                individualInformingResponse.setResultRequestCode(400);
                individualInformingResponseService.add(individualInformingResponse);
                return ResponseEntity.ok(individualInformingResponse);
            }

            try {
                individualInformingRequest.setCodeMO(codeMO);
                individualInformingRequest.setDate_beg(Date.from(Instant.now()));
                individualInformingRequest.setDate_edit(Date.from(Instant.now()));

                individualInformingRequest.getPatients().forEach(patient -> {
                    People people = peopleService.search(patient);
                    if (people != null) {
                        IndividualHistoryInforming individualHistoryInforming =
                                individualHistoryInformingService.getByPeople(people);
                        if (individualHistoryInforming != null){
                            patient.setIhiResponseRecord(individualHistoryInforming);
                            individualHistoryInformingService.update(individualHistoryInforming);
                        } else {
                            IndividualHistoryInforming individualHistoryInformingNew = new IndividualHistoryInforming();
                            individualHistoryInformingNew.setDateBeg(Date.from(Instant.now()));
                            individualHistoryInformingNew.setDateEdit(Date.from(Instant.now()));
                            individualHistoryInformingNew.setPeople(people);
                            individualHistoryInformingService.add(individualHistoryInformingNew);
                            patient.setIhiResponseRecord(individualHistoryInformingNew);
                        }
                    }
                });
                individualInformingRequestService.add(individualInformingRequest, tokenService.getCodeMOWithToken(token));

                individualInformingResponse.setResultRequestCode(200);
                individualInformingResponseService.add(individualInformingResponse);
                return ResponseEntity.ok(individualInformingResponseService
                        .processing(individualInformingRequest, individualInformingResponse, codeMO));

            } catch (Exception e){
                individualInformingResponse.setResultRequestCode(400);
                individualInformingResponseService.add(individualInformingResponse);
            }

        } else {
            individualInformingResponse.setResultRequestCode(403);
            individualInformingResponseService.add(individualInformingResponse);
        }
        return ResponseEntity.ok(individualInformingResponse);
    }

    @PostMapping("/api/test/getIndividualInforming")
    public ResponseEntity<IndividualInformingResponse> registerIndividualInformingTest(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualInformingRequest individualInformingRequest) {

        IndividualInformingResponse individualInformingResponse = new IndividualInformingResponse();
        List<IndividualInformingResponseRecord> individualInformingResponseRecords = new ArrayList<>();
        individualInformingResponse.setResultRequestCode(200);
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
            individualInformingResponse.setResultRequestCode(403);
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
                    individualInformingResponse.setResultRequestCode(401);
                }
            } catch (Exception e){
                individualInformingResponse.setResultRequestCode(400);
            }
        } else {
            individualInformingResponse.setResultRequestCode(403);
        }
        return ResponseEntity.ok(individualInformingResponse);
    }
}
