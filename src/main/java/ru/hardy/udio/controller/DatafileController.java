package ru.hardy.udio.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.ResponseAnswerUdio;
import ru.hardy.udio.domain.struct.*;
import ru.hardy.udio.service.*;

import java.io.IOException;
import java.sql.Date;
import java.time.Instant;

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
                       @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token){

        String newToken = "";
        if (token.contains("Bearer")) newToken = token.substring(7);

        if (tokenService.checkHashKey(dataFile.getLpu(), newToken)) {
            DataUdioRespIdenty dataUdioRespIdenty = dataUdioRespIdentyService.add(new DataUdioRespIdenty(dataUdioRespIdentyGenService
                    .add(new DataUdioRespIdentyGen())));

            new Thread(() -> {
                try {
                    dataFile.setDate_beg(Date.from(Instant.now()));
                    dataFile.setDate_edit(Date.from(Instant.now()));
                    peopleService.treatment(dataFile, dataUdioRespIdenty.getId());
                } catch (InterruptedException | IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            return ResponseEntity.ok(new ResponseAnswerUdio(ResponseAnswerUdio.ResponseAnswerCode.ACCESS,
                    "Данные приняты в обработку",
                    dataUdioRespIdenty));
        }
        return null;
    }


    @PostMapping("/people/{lpucode}")
    public ResponseEntity<ResponseAnswerUdio> getDataStatus(@PathVariable("lpucode") String lpuCode,
                                    @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token,
                                    @RequestHeader(value = "identy") Long identy) {
        String newToken = "";
        if (token.contains("Bearer")) newToken = token.substring(7);
        if (tokenService.checkHashKey(lpuCode, newToken)) {
            DataUdioRespIdenty dataUdioRespIdenty = dataUdioRespIdentyService.getByIdenty(identy);
            if (dataUdioRespIdenty != null) {
                return ResponseEntity.ok(new ResponseAnswerUdio(ResponseAnswerUdio.ResponseAnswerCode.ACCESS,
                        dataUdioRespIdenty.getStatus(),
                        dataUdioRespIdenty));
            }
        }
        return ResponseEntity.ok(new ResponseAnswerUdio(ResponseAnswerUdio.ResponseAnswerCode.ACCESS,
                ResponseAnswerUdio.SAERCH_ERR,
                null));
    }
}
