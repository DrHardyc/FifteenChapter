package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.api.InsuredPerson;
import ru.hardy.udio.domain.api.patientonkocase.PatientOnkoCaseRequestRecord;

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
public class People extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "people_seq")
    @SequenceGenerator(name = "people_seq", allocationSize = 1)
    private Long id;
    private Long idsrz; //идентификатор в БД ЕРЗЛ

    private Integer inv; //инвалидность
    private int mo_attach; //мо прикрепления

    private Date ds; // Дата смерти

    @OneToMany(mappedBy="people", fetch = FetchType.LAZY)
    private List<DNGet> dngets; //случаи прохождения д-наблюдения

    @OneToMany(mappedBy="people", fetch = FetchType.LAZY)
    private List<DNOut> dnouts; //случаи прохождения д-наблюдения

    private Date date_beg;
    private Date date_edit;

    public People(PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord){
        this.setSurname(patientOnkoCaseRequestRecord.getSurname());
        this.setPatronymic(patientOnkoCaseRequestRecord.getPatronymic());
        this.setName(patientOnkoCaseRequestRecord.getName());
        this.setDateBirth(patientOnkoCaseRequestRecord.getDateBirth());
        this.setEnp(patientOnkoCaseRequestRecord.getEnp());
        this.setSex(patientOnkoCaseRequestRecord.getSex());
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public People(ChoosingMORequestRecord choosingMORequestRecord){
        this.setSurname(choosingMORequestRecord.getSurname());
        this.setPatronymic(choosingMORequestRecord.getPatronymic());
        this.setName(choosingMORequestRecord.getName());
        this.setDateBirth(choosingMORequestRecord.getDateBirth());
        this.setEnp(choosingMORequestRecord.getEnp());
        this.setSex(choosingMORequestRecord.getSex());
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public People() {

    }

    public int getAge(){
        return Period.between(this.getDateBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()).getYears();
    }

    public String getFIO(){
        return this.getSurname() + " " + this.getName() + " " + this.getPatronymic();
    }

}
