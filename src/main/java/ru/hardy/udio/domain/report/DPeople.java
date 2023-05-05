package ru.hardy.udio.domain.report;

import lombok.Data;
import ru.hardy.udio.domain.struct.Sex;

import java.util.Date;

@Data
public class DPeople {
    private String lpu;
    private Long idsrz;
    private String fam;
    private String im;
    private String ot;
    private Date dr;
    private String enp;
    private String date_1;
    private String date_2;
    private String iddiag;
    private Sex sex;
    private String iddokt;
    private String nhistory;


}
