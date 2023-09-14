package ru.hardy.udio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCase;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponse;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocase.IndividualHistoryOnkoCaseRequestService;
import ru.hardy.udio.service.apiservice.individualhistoryonkocase.IndividualHistoryOnkoCaseService;

import java.time.Instant;
import java.util.Date;

@RestController
public class IndividualHistoryOnkoCaseController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IndividualHistoryOnkoCaseRequestService individualHistoryOnkoCaseRequestService;

    @Autowired
    private IndividualHistoryOnkoCaseService individualHistoryOnkoCaseService;


    @GetMapping("/api/1.1/getIndividualHistoryOnkoCase/{surname}/{name}/{patronymic}/{datebirth}/{enp}/{sex}")
    public ResponseEntity<IndividualHistoryOnkoCase> registerIndividualHistoryOnkoCase(
            @RequestHeader(name = "token") String token,
            @PathVariable String surname, @PathVariable String name, @PathVariable String patronymic,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date datebirth, @PathVariable String enp,
            @PathVariable int sex) {

        IndividualHistoryOnkoCase IndividualHistoryOnkoCase = new IndividualHistoryOnkoCase();
        IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse = null;
        System.out.println(surname + " | " + name);
        if (tokenService.checkToken(token)) {
        try{
            IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest = new IndividualHistoryOnkoCaseRequest();
            individualHistoryOnkoCaseRequest.setSurname(surname);
            individualHistoryOnkoCaseRequest.setName(name);
            individualHistoryOnkoCaseRequest.setPatronymic(patronymic);
            individualHistoryOnkoCaseRequest.setDateBirth(datebirth);
            individualHistoryOnkoCaseRequest.setEnp(enp);
            individualHistoryOnkoCaseRequest.setSex(sex);
            individualHistoryOnkoCaseRequest.setDateBeg(Date.from(Instant.now()));
            individualHistoryOnkoCaseRequest.setDateEdit(Date.from(Instant.now()));
            individualHistoryOnkoCaseRequestService.add(individualHistoryOnkoCaseRequest);
            individualHistoryOnkoCaseResponse = individualHistoryOnkoCaseService.processing(individualHistoryOnkoCaseRequest);
        } catch (Exception e){

        }

        } else {

        }
        return ResponseEntity.ok(individualHistoryOnkoCaseResponse);
    }

}
