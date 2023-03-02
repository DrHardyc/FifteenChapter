package ru.hardy.udio.domain.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Data
@Table(schema = "udio_datacontrol")
public class DataUdioResp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String fam;
    private String im;
    private String ot;
    private String enp;
    private String codeResp; //Код ошибка + description

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

    public DataUdioResp(People people, String codeResp){
        this.fam = people.getFam();
        this.im = people.getIm();
        this.ot = people.getOt();
        this.enp = people.getEnp();
        this.codeResp = codeResp;
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public DataUdioResp() {

    }
}