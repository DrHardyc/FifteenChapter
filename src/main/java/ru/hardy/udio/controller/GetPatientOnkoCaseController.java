package ru.hardy.udio.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequest;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequestRecord;
import ru.hardy.udio.domain.api.PatientOnkoCaseResponse;
import ru.hardy.udio.domain.api.PatientOnkoCaseResponseRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.*;

import java.awt.event.PaintEvent;
import java.util.ArrayList;
import java.util.List;


@RestController
public class GetPatientOnkoCaseController {

    @Autowired
    private PatientOnkoCaseRequestService patientOnkoCaseRequestService;

    @Autowired
    PatientOnkoCaseService patientOnkoCaseService;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private PatientOnkoCaseResponseService patientOnkoCaseResponseService;

    @Autowired
    private PatientOnkoCaseResponseRecordService patientOnkoCaseResponseRecordService;

    @Autowired
    private PatientOnkoCaseRequestRecordService patientOnkoCaseRequestRecordService;

    @Autowired
    private PeopleService peopleService;


    @PostMapping("/api/1.1/getPatientOnkoCase")
    public ResponseEntity<PatientOnkoCaseResponse> registerPatientOnkoCase(
            @RequestHeader(name = "token") String token,
            @RequestBody PatientOnkoCaseRequest patientOnkoCaseRequest) {

        PatientOnkoCaseResponse patientOnkoCaseResponse = new PatientOnkoCaseResponse();
        if (tokenService.checkToken(token)) {
            try {
                patientOnkoCaseResponse.setResultRequestCode(201);
                patientOnkoCaseResponse.setReqID(patientOnkoCaseRequest.getReqID());
                patientOnkoCaseResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                patientOnkoCaseResponseService.add(patientOnkoCaseResponse);

                patientOnkoCaseRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                patientOnkoCaseRequestService.add(patientOnkoCaseRequest);

                patientOnkoCaseRequestRecordService.addAll(patientOnkoCaseRequest);

                patientOnkoCaseResponse.setResultRequestCode(200);
                patientOnkoCaseResponseService.add(patientOnkoCaseResponse);

                return ResponseEntity.ok(patientOnkoCaseResponseService.processing(patientOnkoCaseRequest, patientOnkoCaseResponse, token));

            } catch (Exception e){
                patientOnkoCaseResponse.setResultRequestCode(400);
                patientOnkoCaseResponseService.add(patientOnkoCaseResponse);
            }

        } else {
            patientOnkoCaseResponse.setResultRequestCode(403);
            patientOnkoCaseResponseService.add(patientOnkoCaseResponse);
        }
        return ResponseEntity.ok(patientOnkoCaseResponse);
    }

    @PostMapping("/api/test/getPatientOnkoCase")
    public ResponseEntity<PatientOnkoCaseResponse> registerPatientOnkoCaseTest(
            @RequestHeader(name = "token") String token,
            @RequestBody PatientOnkoCaseRequest patientOnkoCaseRequest) {

        PatientOnkoCaseResponse patientOnkoCaseResponse = new PatientOnkoCaseResponse();
        List<PatientOnkoCaseResponseRecord> patientOnkoCaseResponseRecords = new ArrayList<>();
        patientOnkoCaseResponse.setResultRequestCode(200);
        if (token.equals("e/SGvhPZm?usABQ9RT-gf9lyeVvYpztQG779xYQZhPyaDZolE=QNldo3ka/chYxrV4Z4mhBMCwtOLouOXihizs0XLEA0RVcLaUmI79L6ZetOl7x8=dDi4ntQ?WRMbI/?")) {
            for (PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord : patientOnkoCaseRequest.getPatients()){
                if (patientOnkoCaseRequestRecord.getSurname().equals("Премудрая") &&
                        patientOnkoCaseRequestRecord.getName().equals("Василиса")  &&
                        patientOnkoCaseRequestRecord.getPatronymic().equals("Ивановна")) {
                    patientOnkoCaseResponseRecords.add(new PatientOnkoCaseResponseRecord(patientOnkoCaseRequestRecord,
                            patientOnkoCaseResponse, 500, "Успешное выполнение обработки записи"));
                } else {
                    patientOnkoCaseResponseRecords.add(new PatientOnkoCaseResponseRecord(patientOnkoCaseRequestRecord,
                            patientOnkoCaseResponse, 503, "Ошибка поиска в СРЗ"));
                }

            }
            patientOnkoCaseResponse.setPatients(patientOnkoCaseResponseRecords);

        } else {
            patientOnkoCaseResponse.setResultRequestCode(403);
            patientOnkoCaseResponseService.add(patientOnkoCaseResponse);
        }
        return ResponseEntity.ok(patientOnkoCaseResponse);
    }


}
