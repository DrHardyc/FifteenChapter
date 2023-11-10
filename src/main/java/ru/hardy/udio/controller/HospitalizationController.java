package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.hardy.udio.domain.api.hospitalization.mo.HospitalizationRequest;
import ru.hardy.udio.domain.api.hospitalization.mo.HospitalizationResponse;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMORequest;
import ru.hardy.udio.domain.api.hospitalization.smo.HospitalizationSMOResponse;
import ru.hardy.udio.service.apiservice.APIRequestService;


@Controller
public class HospitalizationController {
    @Autowired
    private APIRequestService apiRequestService;


    @PostMapping("/api/1.1/setHospitalization")
    public ResponseEntity<HospitalizationResponse> registerHospitalization(
            @RequestHeader(name = "token") String token,
            @RequestBody HospitalizationRequest hospitalizationRequest) {

        return ResponseEntity
                .ok((HospitalizationResponse) apiRequestService
                        .acceptance(token, hospitalizationRequest));
    }


    @PostMapping("/api/1.1/getHospitalization")
    public ResponseEntity<HospitalizationSMOResponse> getHospitalization(
            @RequestHeader(name = "token") String token,
            @RequestBody HospitalizationSMORequest hospitalizationSMORequest) {

        return ResponseEntity
                .ok((HospitalizationSMOResponse) apiRequestService
                        .acceptance(token, hospitalizationSMORequest));
    }

    @PostMapping("/api/test/setHospitalization")
    public ResponseEntity<HospitalizationResponse> registerHospitalizationTest(
            @RequestHeader(name = "token") String token,
            @RequestBody HospitalizationRequest hospitalizationRequest) {

        return ResponseEntity
                .ok((HospitalizationResponse) apiRequestService
                        .acceptance(token, hospitalizationRequest));
    }

}
