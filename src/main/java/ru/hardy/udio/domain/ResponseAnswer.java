package ru.hardy.udio.domain;

import lombok.Data;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;

import java.util.List;

@Data
public class ResponseAnswer {
    private ResponseAnswerCode result;
    private String description;
    private People people;
    private List<DataFilePatient> dataFilePatientList;

    public ResponseAnswer(ResponseAnswerCode responseAnswerCode, String description, People people){
        this.setResult(responseAnswerCode);
        this.setDescription(description);
        this.setPeople(people);
    }

    public ResponseAnswer(ResponseAnswerCode responseAnswerCode, String description, List<DataFilePatient> dataFilePatientList){
        this.setResult(responseAnswerCode);
        this.setDescription(description);
        this.setDataFilePatientList(dataFilePatientList);
    }
    public ResponseAnswer(ResponseAnswerCode responseAnswerCode, String description){
        this.setResult(responseAnswerCode);
        this.setDescription(description);
    }

    public enum ResponseAnswerCode{
        ACCESS, //Успешное завершение
        FORMATERR, //Ошибки относящиеся к формату заполнения DataFile
        SEARCHERR, //Ошибки поиска людей
        TOKENERR //ОШибка токена
    }
}
