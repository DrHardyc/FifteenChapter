package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationRequest;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationResponse;
import ru.hardy.udio.service.apiservice.APIRequestService;


@Controller
public class HospitalizationController {
    @Autowired
    private APIRequestService apiRequestService;


    @PostMapping("/api/1.1/getHospitalization")
    public ResponseEntity<HospitalizationResponse> registerHospitalization(
            @RequestHeader(name = "token") String token,
            @RequestBody HospitalizationRequest hospitalizationRequest) {

        return ResponseEntity
                .ok((HospitalizationResponse) apiRequestService
                        .acceptance(token, hospitalizationRequest));
    }


    @PostMapping("/api/test/getHospitalization")
    public ResponseEntity<HospitalizationResponse> registerHospitalizationTest(
            @RequestHeader(name = "token") String token,
            @RequestBody HospitalizationRequest hospitalizationRequest) {

        return ResponseEntity
                .ok((HospitalizationResponse) apiRequestService
                        .acceptance(token, hospitalizationRequest));
    }

}
