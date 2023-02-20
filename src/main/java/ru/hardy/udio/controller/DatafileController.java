package ru.hardy.udio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.ResponseAnswerUdio;
import ru.hardy.udio.domain.struct.*;
import ru.hardy.udio.service.DataUdioRespIdentyGenService;
import ru.hardy.udio.service.DataUdioRespIdentyService;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.TokenService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DatafileController {

    @Autowired
    private DataUdioRespIdentyService dataUdioRespIdentyService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DataUdioRespIdentyGenService dataUdioRespIdentyGenService;

    @PostMapping(value = "people")
    public ResponseEntity<ResponseAnswerUdio> Search(@RequestBody DataFile dataFile,
                       @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                       @RequestHeader(value = "codeReq") String codeReq) {

        String newToken = "";
        if (token.contains("Bearer")) newToken = token.substring(7);

        if (tokenService.checkHashKey(dataFile.getLpu(), newToken)) {
            switch (codeReq){
                case "SETDATAUDIO" -> {
                    DataUdioRespIdenty dataUdioRespIdenty = dataUdioRespIdentyService.add(new DataUdioRespIdenty(dataUdioRespIdentyGenService
                            .add(new DataUdioRespIdentyGen())));

                    new Thread(() -> {
                        try {
                            peopleService.treatment(dataFile, dataUdioRespIdenty.getId());
                        } catch (InterruptedException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).start();

                    return ResponseEntity.ok(new ResponseAnswerUdio(ResponseAnswerUdio.ResponseAnswerCode.ACCESS,
                            "Данные приняты в обработку",
                            dataUdioRespIdentyService.add(dataUdioRespIdenty)));

                }
                case "TEST" -> {
                }
            }
        }
        return null;
    }

    @GetMapping("/people")
    public People read() {
        People people = new People();
        people.setFam("asdfasdf");
        people.setIm("fdfgdfg");
        people.setOt("fdtqrtqwret");
        return people;
    }
}
