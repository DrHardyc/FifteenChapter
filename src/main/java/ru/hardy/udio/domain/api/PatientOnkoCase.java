package ru.hardy.udio.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(schema = "udio_tfoms")
public class PatientOnkoCase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mainDiagnosis;

    private int firstIdentified;

    private Date dateVisit;
    private int codeMO;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    @JsonIgnore
    private People people;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private PatientOnkoCaseRequest request;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;


    public PatientOnkoCase(){

    }

}
