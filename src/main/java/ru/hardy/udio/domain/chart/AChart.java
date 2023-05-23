package ru.hardy.udio.domain.chart;

import lombok.Data;

@Data
public class AChart {
    private String name;
    private Integer value;

    public AChart(String name, Integer value){
        this.name = name;
        this.value = value;
    }
}
