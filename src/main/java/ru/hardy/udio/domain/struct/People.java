package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Table(schema = "udio_tfoms")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "people_seq")
    @SequenceGenerator(name = "people_seq", allocationSize = 1)
    private Long id;
    private Long idsrz; //идентификатор в БД ЕРЗЛ
    private String fam; // фамилия
    private String im; // имя
    private String ot; //отчество
    private Date dr; //дата рождения
    private String enp; //номер полиса, енп и тд
    private Integer inv; //инвалидность
    private int mo_attach; //мо прикрепления

    private Date ds; // Дата смерти

    @OneToMany(mappedBy="people", fetch = FetchType.LAZY)
    private List<DNGet> dngets; //случаи прохождения д-наблюдения

    @OneToOne
    @JoinColumn(name = "sex_id", nullable = false)
    private Sex sex;
    private Date date_beg;
    private Date date_edit;

    public People(DataFilePatient dataFilePatient){
        this.idsrz = dataFilePatient.getIdsrz();
        this.fam = dataFilePatient.getFam();
        this.ot = dataFilePatient.getOt();
        this.im = dataFilePatient.getIm();
        this.dr = dataFilePatient.getDr();
        this.enp = dataFilePatient.getEnp();
        this.inv = dataFilePatient.getInv();
        this.mo_attach = dataFilePatient.getMo_attach();
        this.sex = dataFilePatient.getSex();
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public People() {

    }

    public int getAge(){
        return Period.between(this.dr.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()).getYears();
    }

    public String getFIO(){
        return this.fam + " " + this.getIm() + " " + this.getOt();
    }

    public String getSexName(){
        return this.getSex().getName();
    }

    public String getSexId(){
        return String.valueOf(this.getSex().getId());
    }


}
