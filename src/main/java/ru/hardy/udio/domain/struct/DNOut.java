package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(schema = "udio_tfoms")
public class DNOut{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dnget_seq")
    @SequenceGenerator(name = "dnget_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    @OneToOne
    @JoinColumn(name = "dfp_id")
    private DataFilePatient dataFilePatient;

    private String nhistory; // номер истории
    private Date date_1; // Дата начала лечения
    private Date date_2; // Дата окончания лечения???
    private Date date_call; //Дата вызова
    private String diag; //диагноз
    private Integer specialization; //профиль по V021
    private Long import_id; // id в БД мо
    private Integer mo; //мо прохождения д-наблюдения
    private Date date_beg;
    private Date date_edit;
    private String reason_dereg; //причина снятия с учета
    private int reason_dereg_code; // 1 - смерть
    public DNOut(DNGet dnGet, People people, int reason_dereg_code) {
        this.mo = dnGet.getMo();
        this.nhistory = dnGet.getNhistory();
        this.date_1 = dnGet.getDate_1();
        this.date_2 = dnGet.getDate_2();
        this.diag = dnGet.getDiag();
        this.date_call = dnGet.getDate_call();
        this.specialization = dnGet.getSpecialization();
        this.import_id = dnGet.getImport_id();
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
        this.people = people;
        this.reason_dereg_code = reason_dereg_code;
        switch (reason_dereg_code){
            case 1 -> reason_dereg = "дата смерти";
            case 2 -> reason_dereg = "другая причина";
        }
    }

    public DNOut() {

    }

    public String getFIO(){
        return this.getPeople().getFIO();
    }

    public LocalDateTime getLocalDateDr(){
        if (this.getPeople().getDr() != null) {
            return this.getPeople().getDr().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }
        return null;
    }

    public LocalDate getLocalDate_1(){
        if (this.getDate_1() != null) {
            return this.getPeople().getDr().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        return null;
    }

    public LocalDate getLocalDateDs(){
        if (this.getPeople().getDs() != null) {
            return this.getPeople().getDs().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        return null;
    }

    public String getEnp(){
        return this.getPeople().getEnp();
    }

    public String getDate_1String(){
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (this.getDate_1() != null) return dateFormat.format(this.getPeople().getDr());
        return null;
    }

    public String getDsString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (this.getPeople().getDs() != null) return dateFormat.format(this.getPeople().getDs());
        return null;
    }

    public String getAge() {
        return String.valueOf(this.getPeople().getAge());
    }

    public String getSex() {
        return String.valueOf(this.getPeople().getSex().getId());
    }
}
