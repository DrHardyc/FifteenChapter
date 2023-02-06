package ru.hardy.udio.domain;

import lombok.Data;

//Данный конструктор предназначен для составления динамических запрос через пользовательский интерфейс. альфа верия v0.01
@Data
public class SelectConstructor {
    //в данном классе нужно перечислить все имеющиеся поля которые даже потенциально
    //будут участвовать в запросе


    private String testparam1;
    private String testparam2;
    private String testparam4;
    private int  testparam3;

    public SelectConstructor(String testparam1, String testparam2, String testparam4, int  testparam3){
        this.testparam1 = testparam1;
        this.testparam2 = testparam2;
        this.testparam3 = testparam3;
        this.testparam4 = testparam4;
    }
}
