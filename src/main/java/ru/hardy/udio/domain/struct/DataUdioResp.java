package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(schema = "udio_datacontrol")
public class DataUdioResp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fam;
    private String im;
    private String ot;
    private String enp;
    private String err; //Код ошибка + description

    public DataUdioResp(People people, String err){
        this.fam = people.getFam();
        this.im = people.getIm();
        this.ot = people.getOt();
        this.enp = people.getEnp();
        this.err = err;
    }

    public DataUdioResp() {

    }
}
