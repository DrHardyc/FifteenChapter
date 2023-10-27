package ru.hardy.udio.domain.struct;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

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
    @SequenceGenerator(name = "people_seq", allocationSize = 3)
    private Long id;

    private Integer sex;

    private Long idsrz; //идентификатор в БД ЕРЗЛ

    private Integer inv; //инвалидность
    private int mo_attach; //мо прикрепления

    private Date ds; // Дата смерти

    @OneToMany(mappedBy="people", fetch = FetchType.LAZY)
    private List<DNGet> dngets; //случаи прохождения д-наблюдения

    @OneToMany(mappedBy="people", fetch = FetchType.LAZY)
    private List<DNOut> dnouts; //случаи прохождения д-наблюдения


    public People(InsuredPerson insuredPerson){
        this.setSurname(insuredPerson.getSurname());
        this.setPatronymic(insuredPerson.getPatronymic());
        this.setName(insuredPerson.getName());
        this.setDateBirth(insuredPerson.getDateBirth());
        this.setEnp(insuredPerson.getEnp());
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }

    public People(String surname, String name, String patronymic, Date dateBirth,
                  String enp, int sex, Long idsrz, Date ds, int lpu){
        this.setSurname(surname);
        this.setName(name);
        this.setPatronymic(patronymic);
        this.setDateBirth(dateBirth);
        this.setEnp(enp);
        this.setSex(sex);
        this.setIdsrz(idsrz);
        this.setDs(ds);
        this.setMo_attach(lpu);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
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
