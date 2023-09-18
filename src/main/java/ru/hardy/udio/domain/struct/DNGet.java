package ru.hardy.udio.domain.struct;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(schema = "udio_tfoms")
public class DNGet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "dnget_seq")
    @SequenceGenerator(name = "dnget_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    @OneToOne
    @JoinColumn(name = "dgroup_id")
    private DGroup dGroup;

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

    public DNGet(DataFilePatient dataFilePatient, DGroup dGroup, People people) {
        this.mo = dataFilePatient.getDatafile().getMo();
        this.nhistory = dataFilePatient.getNhistory();
        this.date_1 = dataFilePatient.getDate_1();
        this.date_2 = dataFilePatient.getDate_2();
        this.diag = dataFilePatient.getDiag();
        this.date_call = dataFilePatient.getDate_call();
        this.specialization = dataFilePatient.getSpecialization();
        this.import_id = dataFilePatient.getDatafile().getImport_id();
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
        this.dGroup = dGroup;
        this.people = people;
        this.dataFilePatient = dataFilePatient;
    }

    public DNGet() {

    }

    public int getAge() {
        return Period.between(this.getPeople().getDateBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()).getYears();
    }
    public String getFIO() {
        return this.getPeople().getFIO();
    }

    public int getMOAttach(){
        return this.getPeople().getMo_attach();
    }

    public int getPeopleInv(){
        return this.getPeople().getInv();
    }

    public LocalDate getLocalDateTimeDate_1() {
        if (this.date_1 != null) {
            return this.getDate_1().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        return null;
    }

    public LocalDate getLocalDateTimeDate_call() {
        if (this.date_call != null) {
            return this.getDate_call().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        return null;
    }

    public String getDate1String() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (this.getDate_1() != null) return dateFormat.format(this.getDate_1());
        return null;
    }

    public String getDateCallString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (this.getDate_call() != null) return dateFormat.format(this.getDate_call());
        return null;
    }

    public int getSex() {
        return this.getPeople().getSex();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        DNGet dnGet = (DNGet) o;
//        return this.getId().equals(dnGet.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(this.getId());
//    }

}
