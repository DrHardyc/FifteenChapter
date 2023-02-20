package ru.hardy.udio.domain.struct;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(schema = "udio_tfoms")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long idsrz;
    private String fam;
    private String im;
    private String ot;
    private Date dr;
    private String enp;

    @ElementCollection
    @CollectionTable(name = "nhistory_people", schema = "udio_tfoms")
    private List<String> nhistory;

    @OneToOne
    @JoinColumn(name = "sex_id", nullable = false)
    private Sex sex;

    @OneToOne
    @JoinColumn(name = "dfp_id", nullable = false)
    private DataFilePatient dataFilePatient;

    private Date dedit;

    public People(DataFilePatient dataFilePatient){
        this.idsrz = dataFilePatient.getIdsrz();
        this.fam = dataFilePatient.getFam();
        this.ot = dataFilePatient.getOt();
        this.im = dataFilePatient.getIm();
        this.dr = dataFilePatient.getDr();
        this.enp = dataFilePatient.getEnp();
        this.nhistory = dataFilePatient.getNhistory();
        this.sex = dataFilePatient.getSex();
        this.dedit = Date.from(Instant.now());
        this.dataFilePatient = dataFilePatient;
    }


    public People() {

    }
}
