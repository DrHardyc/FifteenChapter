package ru.hardy.udio.domain.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Getter
@Setter
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
    private Integer specialization; //специальность врача
    private Date date_1;
    private Date date_2;

    private String srz_status; // найден/ не найден
    private int srz_status_code; //1 - успешно добавлен; 2 - не найден в срз; 3 - не добавлен; 4 - найден по енп; 5- дубль; 6 - ошибка поиска срз
    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

    public DataFilePatient(String fam, String im, String ot, Date dr, String enp, Integer mo_attach, Sex sex, Integer inv, String nhistory,
                           String diag, Date date_call, Integer specialization, Date date_1, Date date_2, int srz_status_code, Long idsrz, DataFile dataFile) {
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
        this.specialization = specialization;
        this.date_1 = date_1;
        this.date_2 = date_2;
        this.idsrz = idsrz;
        this.srz_status_code = srz_status_code;
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
        this.datafile = dataFile;

        switch (srz_status_code){
            case 1 -> srz_status = "успешно добавлен";
            case 2 -> srz_status = "не найден в срз";
            case 3 -> srz_status = "не добавлен";
            case 4 -> srz_status = "найден по енп";
            case 5 -> srz_status = "дубль";
            case 6 -> srz_status = "ошибка поиска срз";
        }
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
