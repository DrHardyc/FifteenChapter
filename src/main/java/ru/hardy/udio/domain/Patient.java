package ru.hardy.udio.domain;

import lombok.Data;

@Data
public class Patient {
    private String fam;
    private String im;
    private String ot;
    private String polis;

    public Patient(String fam, String im, String ot, String polis){
        this.fam = fam;
        this.im = im;
        this.ot = ot;
        this.polis = polis;
    }
}
