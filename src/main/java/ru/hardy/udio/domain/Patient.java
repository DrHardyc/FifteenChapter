package ru.hardy.udio.domain;

import lombok.Data;

@Data
public class Patient {
    private Long id;
    private String fam;
    private String im;
    private String ot;
    private String polis;
}
