package ru.hardy.udio.domain.regul.grid;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

import static ru.hardy.udio.service.UtilService.dateToLocalDate;

@Data
public class RegULUI {

    private String inn;
    private String ogrn;
    private String kpp;
    private String regNFoms;
    private String name;
    private String address;
    private String status;
    private Date date;

    public LocalDate getLocalDateDate(){
        return dateToLocalDate(this.date);
    }
}
