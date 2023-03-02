package ru.hardy.udio.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.hardy.udio.domain.struct.DataUdioRespIdenty;

import java.time.Instant;
import java.util.Date;

@Data
public class ResponseAnswerUdio {
    public static final String PROCESSING = "В обработке";
    public static final String PROCESSING_ERR = "Ошибка обработки";
    public static final String PROCESSING_END = "Обработка завершена";
    public static final String SAERCH_ERR = "Данные не найдены";

    private ResponseAnswerCode result;
    private String description;
    private DataUdioRespIdenty dataUdioResp;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

    public ResponseAnswerUdio(ResponseAnswerCode responseAnswerCode, String description, DataUdioRespIdenty dataUdioResp){
        this.setResult(responseAnswerCode);
        this.setDescription(description);
        this.setDataUdioResp(dataUdioResp);
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public enum ResponseAnswerCode{
        ACCESS, //Успешное завершение
        FORMATERR, //Ошибки относящиеся к формату заполнения DataFile
        SEARCHERR, //Ошибки поиска людей
        TOKENERR //ОШибка токена
    }
}
