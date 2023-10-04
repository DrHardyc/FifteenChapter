package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.service.TokenService;

import java.time.Instant;
import java.util.Date;

@Controller
public class IndividualHistoryInformingController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/api/1.1/getIndividualHistoryInforming")
    public ResponseEntity<IndividualHistoryInformingResponse> registerCIndividualHistoryInforming(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryInformingRequest individualHistoryInformingRequest) {

//        IndividualHistoryInformingResponse individualHistoryInformingResponse = new IndividualHistoryInformingResponse();
//        individualHistoryInformingResponse.setResultRequestCode(201);
//        individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
//        int codeMO = tokenService.getCodeMOWithToken(token);
//
//        if (tokenService.checkToken(token)) {
//            IndividualHistoryInformingResponse individualHistoryInformingResponseFromDB =
//                    individualHistoryInformingResponseService.getWithReqId(individualHistoryInformingRequest.getReqID(), codeMO);
//            if (individualHistoryInformingResponseFromDB != null) {
//                individualHistoryInformingResponse.setResultRequestCode(402);
//                IndividualHistoryInformingResponseService.add(individualHistoryInformingResponse);
//                return ResponseEntity.ok(individualHistoryInformingResponse);
//            }
//
//            if (individualHistoryInformingRequest.getPatients() == null) {
//                individualHistoryInformingResponse.setResultRequestCode(400);
//                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
//                return ResponseEntity.ok(individualHistoryInformingResponse);
//            }
//            try {
//                individualHistoryInformingResponse.setCodeMO(codeMO);
//                individualHistoryInformingResponse.setDate_beg(Date.from(Instant.now()));
//                individualHistoryInformingResponse.setDate_edit(Date.from(Instant.now()));
//                individualHistoryInformingResponse.setReqID(individualHistoryInformingRequest.getReqID());
//                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
//
//                individualHistoryInformingRequest.setCodeMO(codeMO);
//                individualHistoryInformingRequest.setDate_beg(Date.from(Instant.now()));
//                individualHistoryInformingRequest.setDate_edit(Date.from(Instant.now()));
//                individualHistoryInformingRequestService.add(individualHistoryInformingRequest);
//
//                individualHistoryInformingRequest.getPatients().forEach(patientRequest -> {
//                    patientRequest.setRequest(individualHistoryInformingRequest);
//                    patientRequest.setDate_beg(Date.from(Instant.now()));
//                    patientRequest.setDate_edit(Date.from(Instant.now()));
//                });
//                individualHistoryInformingRequestRecordService.addAll(individualHistoryInformingRequest.getPatients());
//
//                return ResponseEntity.ok(individualHistoryInformingResponseService
//                        .processing(individualHistoryInformingRequest, individualHistoryInformingResponse, codeMO));
//
//            } catch (Exception e) {
//                individualHistoryInformingResponse.setResultRequestCode(400);
//                individualHistoryInformingResponseService.add(individualHistoryInformingResponse);
//            }
//
//        } else {
//            individualHistoryInformingResponse.setResultRequestCode(403);
//            choosingMOResponseService.add(individualHistoryInformingResponse);
//        }
//        return ResponseEntity.ok(individualHistoryInformingResponse);
        return null;
    }
}
