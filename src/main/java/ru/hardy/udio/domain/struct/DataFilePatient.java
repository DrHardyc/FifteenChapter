package ru.hardy.udio.domain.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class DataFilePatient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "data_file_patient_seq")
    @SequenceGenerator(name = "data_file_patient_seq", allocationSize = 1)
    private Long id;

    //People
    private Long idsrz; //идентификатор в БД ЕРЗЛ
    private String fam; // фамилия
    private String im; // имя
    private String ot; //отчество
    private Date dr; //дата рождения
    private String enp; //номер полиса, енп и тд
    private Integer mo_attach; //мо прикрепления
    private Integer inv; //инвалидность
    @OneToOne
    @JoinColumn(name = "sex_id", nullable = false)
    private Sex sex;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "datafile_id")
    private DataFile datafile;

    //DNGet
    private String nhistory; // номер истории
    private String diag; // диагноз
    private Date date_call; //Дата вызова
    private Integer profil; //профиль по V021
    private Date date_1;
    private Date date_2;
    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

    public DataFilePatient(String fam, String im, String ot, Date dr, String enp, Integer mo_attach, Sex sex, Integer inv, String nhistory,
                           String diag, Date date_call, Integer profil, Date date_1, Date date_2, DataFile dataFile) {
        this.fam = fam;
        this.im = im;
        this.ot = ot;
        this.dr = dr;
        this.enp = enp;
        this.mo_attach = mo_attach;
        this.sex = sex;
        this.inv = inv;
        this.nhistory = nhistory;
        this.diag = diag;
        this.date_call = date_call;
        this.profil = profil;
        this.date_1 = date_1;
        this.date_2 = date_2;
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
        this.datafile = dataFile;
    }

    public DataFilePatient(){
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public String getFIO(){
        return this.getFam() + " " + this.getIm() + " " + this.getOt();
    }

    public Integer getAge() {
        return Period.between(this.dr.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()).getYears();
    }
}
