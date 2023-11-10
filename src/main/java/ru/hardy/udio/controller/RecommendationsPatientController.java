package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientRequest;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientResponse;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMORequest;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMOResponse;
import ru.hardy.udio.service.apiservice.APIRequestService;

@Controller
public class RecommendationsPatientController {

    @Autowired
    private APIRequestService apiRequestService;

    @PostMapping("/api/1.1/setRecommendationsPatient")
    public ResponseEntity<RecommendationsPatientResponse> registerHospitalization(
            @RequestHeader(name = "token") String token,
            @RequestBody RecommendationsPatientRequest recommendationsPatientRequest) {

        return ResponseEntity
                .ok((RecommendationsPatientResponse) apiRequestService
                        .acceptance(token, recommendationsPatientRequest));
    }

    @PostMapping("/api/1.1/getRecommendationsPatient")
    public ResponseEntity<RecommendationsPatientSMOResponse> getHospitalization(
            @RequestHeader(name = "token") String token,
            @RequestBody RecommendationsPatientSMORequest recommendationsPatientSMORequest) {

        return ResponseEntity
                .ok((RecommendationsPatientSMOResponse) apiRequestService
                        .acceptance(token, recommendationsPatientSMORequest));
    }

    @PostMapping("/api/test/setRecommendationsPatient")
    public ResponseEntity<RecommendationsPatientResponse> registerHospitalizationTest(
            @RequestHeader(name = "token") String token,
            @RequestBody RecommendationsPatientRequest recommendationsPatientRequest) {

        return ResponseEntity
                .ok((RecommendationsPatientResponse) apiRequestService
                        .acceptance(token, recommendationsPatientRequest));
    }
}
