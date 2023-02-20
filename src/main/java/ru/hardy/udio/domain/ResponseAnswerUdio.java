package ru.hardy.udio.domain;

import lombok.Data;
import ru.hardy.udio.domain.struct.DataUdioRespIdenty;

@Data
public class ResponseAnswerUdio {
    private ResponseAnswerCode result;
    private String description;
    private DataUdioRespIdenty dataUdioResp;

    public ResponseAnswerUdio(ResponseAnswerCode responseAnswerCode, String description, DataUdioRespIdenty dataUdioResp){
        this.setResult(responseAnswerCode);
        this.setDescription(description);
        this.setDataUdioResp(dataUdioResp);
    }

    public enum ResponseAnswerCode{
        ACCESS, //Успешное завершение
        FORMATERR, //Ошибки относящиеся к формату заполнения DataFile
        SEARCHERR, //Ошибки поиска людей
        TOKENERR //ОШибка токена
    }
}
