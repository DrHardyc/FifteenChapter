package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.hardy.udio.domain.api.padatepatinetssmo.PADataPatientSMOResponse;
import ru.hardy.udio.domain.api.padatepatinetssmo.PADataPatientSMORequest;
import ru.hardy.udio.service.apiservice.APIRequestService;

@Controller
public class PADataPatientsSMOController {

    @Autowired
    private APIRequestService apiRequestService;

    @PostMapping("/api/1.1/getPADataPatientSMO")
    public ResponseEntity<PADataPatientSMOResponse> getPADataPatientSMO(
            @RequestHeader(name = "token") String token,
            @RequestBody PADataPatientSMORequest paDataPatientSMORequest) {

        return ResponseEntity
                .ok((PADataPatientSMOResponse) apiRequestService
                        .acceptance(token, paDataPatientSMORequest));
    }
}
