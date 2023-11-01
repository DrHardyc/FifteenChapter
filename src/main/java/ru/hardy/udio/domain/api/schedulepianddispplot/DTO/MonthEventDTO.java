package ru.hardy.udio.domain.api.schedulepianddispplot.DTO;

import lombok.Data;

@Data
public class MonthEventDTO {
    private int month;
    private int quantityPlan;

    public MonthEventDTO(int month, int quantityPlan){
        this.setMonth(month);
        this.setQuantityPlan(quantityPlan);
    }
}
