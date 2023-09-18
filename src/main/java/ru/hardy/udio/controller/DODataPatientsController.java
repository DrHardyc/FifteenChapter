package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsRequest;
import ru.hardy.udio.domain.api.dodatapatients.DODataPatientsResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.choosingmoservice.ChoosingMOResponseService;
import ru.hardy.udio.service.apiservice.dodatapatientsservice.DODataPatientsRequestRecordService;
import ru.hardy.udio.service.apiservice.dodatapatientsservice.DODataPatientsRequestService;
import ru.hardy.udio.service.apiservice.dodatapatientsservice.DoDataPatientsResponseService;

import java.time.Instant;
import java.util.Date;

@RestController
public class DODataPatientsController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DoDataPatientsResponseService doDataPatientsResponseService;

    @Autowired
    private DODataPatientsRequestService doDataPatientsRequestService;

    @Autowired
    private DODataPatientsRequestRecordService doDataPatientsRequestRecordService;


    @PostMapping("/api/1.1/getDODataPatients")
    public ResponseEntity<DODataPatientsResponse> registerChoosingMO(
            @RequestHeader(name = "token") String token,
            @RequestBody DODataPatientsRequest doDataPatientsRequest) {

        DODataPatientsResponse doDataPatientsResponse = new DODataPatientsResponse();
        doDataPatientsResponse.setResultRequestCode(201);
        doDataPatientsResponseService.add(doDataPatientsResponse);

        if (tokenService.checkToken(token)) {
            DODataPatientsResponse doDataPatientsResponseFromDB =
                    doDataPatientsResponseService.getWithReqId(doDataPatientsRequest.getReqID(),
                            tokenService.getCodeMOWithToken(token));
            if (doDataPatientsResponseFromDB != null){
                doDataPatientsResponse.setResultRequestCode(402);
                doDataPatientsResponseService.add(doDataPatientsResponse);
                return ResponseEntity.ok(doDataPatientsResponse);
            }

            if (doDataPatientsRequest.getPatients() == null){
                doDataPatientsResponse.setResultRequestCode(400);
                doDataPatientsResponseService.add(doDataPatientsResponse);
                return ResponseEntity.ok(doDataPatientsResponse);
            }
            try {
                doDataPatientsResponse.setCodeMO(tokenService.getCodeMOWithToken(token));
                doDataPatientsResponse.setDateBeg(Date.from(Instant.now()));
                doDataPatientsResponse.setDateEdit(Date.from(Instant.now()));
                doDataPatientsResponse.setReqID(doDataPatientsRequest.getReqID());
                doDataPatientsRequestService.add(doDataPatientsRequest);

                doDataPatientsRequest.setCodeMO(tokenService.getCodeMOWithToken(token));
                doDataPatientsRequest.setDate_beg(Date.from(Instant.now()));
                doDataPatientsRequest.setDate_edit(Date.from(Instant.now()));
                doDataPatientsRequestService.add(doDataPatientsRequest);

                doDataPatientsRequest.getPatients().forEach(patientRequest -> {
                    patientRequest.setRequest(doDataPatientsRequest);
                    patientRequest.setDate_beg(Date.from(Instant.now()));
                    patientRequest.setDate_edit(Date.from(Instant.now()));
                });
                doDataPatientsRequestRecordService.addAll(doDataPatientsRequest.getPatients());

                return ResponseEntity.ok(doDataPatientsResponseService
                        .processing(doDataPatientsRequest, doDataPatientsResponse, tokenService.getCodeMOWithToken(token)));

            } catch (Exception e){
                doDataPatientsResponse.setResultRequestCode(400);
                doDataPatientsResponseService.add(doDataPatientsResponse);
            }

        } else {
            doDataPatientsResponse.setResultRequestCode(403);
            doDataPatientsResponseService.add(doDataPatientsResponse);
        }
        return ResponseEntity.ok(doDataPatientsResponse);
    }

}
