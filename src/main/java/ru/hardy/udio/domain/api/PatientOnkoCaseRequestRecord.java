package ru.hardy.udio.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class PatientOnkoCaseRequestRecord extends InsuredPerson{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mainDiagnosis;
    private int firstIdentified;
    private Date dateVisit;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private PatientOnkoCaseRequest request;

}
