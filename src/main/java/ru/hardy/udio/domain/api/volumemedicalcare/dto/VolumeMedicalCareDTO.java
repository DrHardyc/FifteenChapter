package ru.hardy.udio.domain.api.volumemedicalcare.dto;

import lombok.Data;

@Data
public class VolumeMedicalCareDTO {
    private String name;
    private int day1;
    private int day2;
    private int day3;
    private int day4;
    private int day5;
    private int day6;
    private int day7;
    private VolumeMedicalCareDTO parent;

    public VolumeMedicalCareDTO(){

    }
}
