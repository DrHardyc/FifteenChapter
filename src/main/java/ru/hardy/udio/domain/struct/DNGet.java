package ru.hardy.udio.domain.struct;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class DNGet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dbeg;
    private String iddokt;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    private People people;

    private String lpu;

    @ElementCollection
    @CollectionTable(name ="nhistory_dnget", schema = "udio_tfoms")
    private List<String> nhistory;
    private Date date_1;
    private String iddiag;

    private Date date_beg;
    private Date date_edit;

    public DNGet(DataFile dataFile, DataFilePatient dataFilePatient, People people){
        this.dbeg = Date.from(Instant.now());
        this.iddokt = dataFilePatient.getIddokt();
        this.people = people;
        this.lpu = dataFile.getLpu();
        this.nhistory = dataFilePatient.getNhistory();
        this.date_1 = dataFilePatient.getDate_1();
        this.iddiag = dataFilePatient.getIddiag();
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public DNGet() {

    }
}
