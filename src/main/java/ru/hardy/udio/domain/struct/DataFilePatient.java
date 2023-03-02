package ru.hardy.udio.domain.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(schema = "udio_tfoms")
public class DataFilePatient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idsrz;
    private String fam;
    private String im;
    private String ot;
    private Date dr;
    @OneToOne
    @JoinColumn(name = "sex_id", nullable = false)
    private Sex sex;
    private Date dbeg;
    private String iddokt;
    @ElementCollection
    @CollectionTable(name ="nhistory_dfp", schema = "udio_tfoms")
    private List<String> nhistory;
    private Date date_1;
    private Date date_2;
    private String iddiag;
    private String enp;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

}
