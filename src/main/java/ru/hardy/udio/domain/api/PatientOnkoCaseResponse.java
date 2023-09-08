package ru.hardy.udio.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(schema = "udio_datacontrol")
public class PatientOnkoCaseResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int resultRequestCode;
    private String reqID;
    private int codeMO;
    private int numberRecordsProcessed;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<PatientOnkoCaseResponseRecord> patients;

    public PatientOnkoCaseResponse() {

    }
}
