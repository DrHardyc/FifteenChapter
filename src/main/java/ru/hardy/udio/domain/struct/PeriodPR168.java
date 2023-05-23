package ru.hardy.udio.domain.struct;

public enum PeriodPR168 {
    //не реже 1 раза в год
    ONE_1YEAR,
    // не реже 2 раз в год
    TWO_1YEAR,
    //не реже 1 раз в 3 месяца, затем не реже 1 раза в 6 месяцев
    ONE_3MONTH_IN_FIRST_1YEAR_AND_OTHER_ONE_6MONTH,
    // не реже 1 раза в 6 месяцев
    ONE_6MONTH,
    // не реже 4 раз в год
    FOUR_1YEAR,
    // не реже 1 раза в 4 месяца
    ONE_4MONTH,
    // не реже 1 раза в 2 года
    ONE_2YEAR,
    // не реже 2 раз в год в течение первых 2 лет диспансерного наблюдения
    TWO_1YEAR_IN_FIRST_2YEAR_AND_OTHER_ONE_1YEAR,
    // не реже чем 1 раз в 6 месяцев в течение первого года, далее - ежегодно
    ONE_6MONTH_IN_FIRST_1YEAR_AND_OTHER_ONE_1YEAR,
    //не позднее 3 месяцев после хирургического лечения, далее -
    // не реже 2 раз в год в течение 2 лет, далее ежегодно в течение 20 лет
    ONE_3MONTH_OTHER_TWO_1YEAR_IN_2YEAR_OTHER_ONE_1YEAR_IN_DUR_20YEARS,
    //В соответствии с клиническими рекомендациями
    CLINIC_RECOMMEND,
    // не реже 1 раза в 3 месяца первый год, далее 2 раза в год
    ONE_3MONTH_IN_FIRST_YEAR_OTHER_TWO_1YEAR
}
