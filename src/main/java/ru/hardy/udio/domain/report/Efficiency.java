package ru.hardy.udio.domain.report;

import lombok.Data;

import java.util.Date;

@Data
public class Efficiency {
    private String mo;
    private String concat;
    private String disp;
    private Date date_1;

    public Efficiency(String mo, String concat, String disp, Date date_1){
        this.mo = mo;
        this.concat = concat;
        this.disp = disp;
        this.date_1 = date_1;
    }
}
