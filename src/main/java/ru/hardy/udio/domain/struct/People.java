package ru.hardy.udio.domain.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long idsrz;
    private String fam;
    private String im;
    private String ot;
    private Date dr;
    private String enp;

    @ElementCollection
    @CollectionTable(name = "nhistory_people", schema = "udio_util")
    private List<String> nhistory;

    @OneToOne
    @JoinColumn(name = "sex_id", nullable = false)
    private Sex sex;
    @JsonIgnore
    private Date dedit;

    public People(DataFilePatient dataFilePatient){
        this.setFam(dataFilePatient.getFam());
        this.setIm(dataFilePatient.getIm());
        this.setOt(dataFilePatient.getOt());
        this.setDr(dataFilePatient.getDr());
        this.setSex(dataFilePatient.getSex());
        this.setNhistory(dataFilePatient.getNhistory());
        this.setEnp(dataFilePatient.getEnp());
    }

    public People(People people){
        this.setFam(people.getFam());
        this.setIm(people.getIm());
        this.setOt(people.getOt());
        this.setDr(people.getDr());
        this.setSex(people.getSex());
        this.setNhistory(people.getNhistory());
        this.setEnp(people.getEnp());
        this.setIdsrz(people.getIdsrz());
        this.setEnp(people.getEnp());
    }

    public People() {

    }
}
