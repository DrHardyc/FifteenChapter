package ru.hardy.udio.domain.struct;



public enum Duration {
    //Пожизненно
    ALL,
    // в течении 1 года
    ONE_YEAR,
    // В течении 3 лет
    THREE_YEAR,
    // В течении 5 лет
    FIVE_YEAR,
    // До 10 лет
    TEN_YEAR,
    // 20 лет
    TWENTY_YEAR,
    // До 60 лет, но не менее 5 лет
    MIN_5YEAR_TO_60YEAR
}
