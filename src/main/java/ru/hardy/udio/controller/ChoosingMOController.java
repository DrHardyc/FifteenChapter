package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponseRecord;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.APIRequestService;
import ru.hardy.udio.service.apiservice.choosingmoservice.ChoosingMOResponseService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ChoosingMOController {

    @Autowired
    private ChoosingMOResponseService choosingMOResponseService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private APIRequestService apiRequestService;

    @PostMapping("/api/1.1/setChoosingMO")
    public ResponseEntity<ChoosingMOResponse> registerChoosingMO(
            @RequestHeader(name = "token") String token,
            @RequestBody ChoosingMORequest choosingMORequest) {
        return ResponseEntity
                .ok((ChoosingMOResponse) apiRequestService
                        .acceptance(token, choosingMORequest));
    }

    @PostMapping("/api/test/setChoosingMO")
    public ResponseEntity<ChoosingMOResponse> registerChoosingMOTest(
            @RequestHeader(name = "token") String token,
            @RequestBody ChoosingMORequest choosingMORequest) {

        ChoosingMOResponse choosingMOResponse = new ChoosingMOResponse();
        List<ChoosingMOResponseRecord> choosingMOResponseRecords = new ArrayList<>();
        choosingMOResponse.setResultResponseCode(200);
        choosingMOResponse.setReqID(choosingMORequest.getReqID());
        choosingMOResponse.setNumberRecordsProcessed(1);
        if (token.equals("e/SGvhPZm?usABQ9RT-gf9lyeVvYpztQG779xYQZhPyaDZolE=QNldo3ka/chYxrV4Z4mhBMCwtOLouOXihizs0XLEA0RVcLaUmI79L6ZetOl7x8=dDi4ntQ?WRMbI/?")) {
            for (ChoosingMORequestRecord choosingMOResponseRecord : choosingMORequest.getPatients()){
                if (choosingMOResponseRecord.getSurname().equals("Премудрая") &&
                        choosingMOResponseRecord.getName().equals("Василиса") &&
                        choosingMOResponseRecord.getPatronymic().equals("Ивановна")) {
                    choosingMOResponseRecords.add(new ChoosingMOResponseRecord(choosingMOResponseRecord,
                            choosingMOResponse, 500, "Успешное выполнение обработки записи"));
                } else {
                    choosingMOResponseRecords.add(new ChoosingMOResponseRecord(choosingMOResponseRecord,
                            choosingMOResponse, 503, "Ошибка поиска в СРЗ"));
                }

            }
            choosingMOResponse.setPatients(choosingMOResponseRecords);

        } else {
            choosingMOResponse.setResultResponseCode(403);
            choosingMOResponseService.add(choosingMOResponse);
        }
        return ResponseEntity.ok(choosingMOResponse);
    }

    @GetMapping("/api/1.1/getChoosingMO/{reqID}")
    public ResponseEntity<ChoosingMOResponse> getChoosingMOAsynchronous(
            @RequestHeader(name = "token") String token,
            @PathVariable(name = "reqID") String reqID ){

        int codeMO = tokenService.getCodeMOWithToken(token);

        ChoosingMOResponse choosingMOResponse = new ChoosingMOResponse();
        if (tokenService.checkToken(token)) {
            try {
                ChoosingMOResponse choosingMOResponseFromDB = choosingMOResponseService.getWithReqId(reqID, codeMO);
                if (choosingMOResponseFromDB != null){
                    return ResponseEntity.ok(choosingMOResponseFromDB);
                } else {
                    choosingMOResponse.setResultResponseCode(401);
                }
            } catch (Exception e){
                choosingMOResponse.setResultResponseCode(400);
            }
        } else {
            choosingMOResponse.setResultResponseCode(403);
        }
        return ResponseEntity.ok(choosingMOResponse);
    }
}
