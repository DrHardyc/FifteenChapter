package ru.hardy.udio.controller;


import lombok.Data;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hardy.udio.domain.ResponseAnswer;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DatafileController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping(value = "people")
    public ResponseEntity<List<ResponseAnswer>> Search(@RequestBody DataFile dataFile,
                       @RequestHeader(value = HttpHeaders.AUTHORIZATION) String token) throws SQLException {
        List<ResponseAnswer> responseAnswers = new ArrayList<>();
        if (token.equals("Bearer 12345")) {
            for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()) {
                People peopleszr = peopleService.searchFromSRZ(dataFilePatient);
                if (peopleszr != null){
                    if (peopleService.searchFromUdio(dataFilePatient) != null){
                        responseAnswers.add(new ResponseAnswer(ResponseAnswer.ResponseAnswerCode.ACCESS,
                                "Данные успешно обновлены", peopleService.update(peopleszr)));
                    } else {
                        responseAnswers.add(new ResponseAnswer(ResponseAnswer.ResponseAnswerCode.ACCESS,
                                "Данные успешно добавлены", peopleService.save(new People(peopleszr))));
                    }
                } else {
                    responseAnswers.add(new ResponseAnswer(ResponseAnswer.ResponseAnswerCode.SEARCHERR,
                            "В базе данных застрахованных лиц данные не найдены", new People(dataFilePatient)));
                }
            }
        } else responseAnswers.add(new ResponseAnswer(ResponseAnswer.ResponseAnswerCode.TOKENERR,
                    "Ключ не распознан"));
        return ResponseEntity.ok(responseAnswers);
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
