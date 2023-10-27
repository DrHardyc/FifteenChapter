package ru.hardy.udio.domain.generic;

import lombok.Data;

@Data
public class ResultProcessingClass<T> {
    private int resultCode;

    private T processingClass;

    public ResultProcessingClass(int resultCode, T processingClass){
        this.setResultCode(resultCode);
        this.setProcessingClass(processingClass);
    }

}
