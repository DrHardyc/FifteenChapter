package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponse;
import ru.hardy.udio.service.apiservice.APIRequestService;

@RestController
public class IndividualHistoryOnkoCaseController {
    @Autowired
    private APIRequestService requestService;

    @PostMapping("/api/1.1/getIndividualHistoryOnkoCase")
    public ResponseEntity<IndividualHistoryOnkoCaseResponse> registerIndividualHistoryOnkoCase(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest) {
        return ResponseEntity
                .ok((IndividualHistoryOnkoCaseResponse) requestService
                        .acceptance(token, individualHistoryOnkoCaseRequest));
    }

    @PostMapping("/api/test/getIndividualHistoryOnkoCase")
    public ResponseEntity<IndividualHistoryOnkoCaseResponse> registerIndividualHistoryOnkoCaseTest(
            @RequestHeader(name = "token") String token,
            @RequestBody IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest) {
        return ResponseEntity.ok((IndividualHistoryOnkoCaseResponse) requestService.acceptance(token, individualHistoryOnkoCaseRequest));
    }
}
