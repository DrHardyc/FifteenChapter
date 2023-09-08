package ru.hardy.udio.domain.struct.dto;

import lombok.Data;
import ru.hardy.udio.domain.struct.Sex;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
public class DNOutDto {
    private String fam;
    private String im;
    private String ot;
    private Date dr;
    private String enp;
    private Sex sex;
    private Date ds;
    private String diags;
    private String date_1;

    public DNOutDto(String fam, String im, String ot, Date dr, String enp, Sex sex, Date ds, String diags, String date_1){
        this.fam = fam;
        this.im = im;
        this.ot = ot;
        this.dr = dr;
        this.enp = enp;
        this.sex = sex;
        this.ds = ds;
        this.diags = diags;
        this.date_1 = date_1;
    }

    public String getFIO(){
        return this.getFam() + " " + this.getIm() + " " + this.getOt();
    }

    public String getStringSexId(){
        return String.valueOf(this.getSex().getId());
    }

    public int getAge() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return (Integer.parseInt(dateFormat.format(Date.from(Instant.now()))) - Integer.parseInt(dateFormat.format(this.getDr()))) / 10000;
    }

    public LocalDate getLocalDateDs(){
        if (this.getDs() != null) {
            return Instant.ofEpochMilli(this.getDs().getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        return null;
    }

    public int getNumberSex(){
        return this.getSex().getId().intValue();
    }
}
