package ru.hardy.udio.domain.api;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(schema = "udio_datacontrol")
public class PatientOnkoCaseRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reqID;
    private int codeMO;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY)
    private List<PatientOnkoCaseRequestRecord> patients;
}
