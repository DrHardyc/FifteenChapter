package ru.hardy.udio.domain.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(schema = "udio_datacontrol")
@Entity
public class PatientOnkoCaseResponseRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private PatientOnkoCaseResponse response;

    private int respCode;
    private String respMessage;

    public PatientOnkoCaseResponseRecord(PatientOnkoCaseRequestRecord patient, PatientOnkoCaseResponse response, int respCode, String respMessage) {
        this.setSurname(patient.getSurname());
        this.setName(patient.getName());
        this.setPatronymic(patient.getPatronymic());
        this.setEnp(patient.getEnp());
        this.setDateBirth(patient.getDateBirth());
        this.setSex(patient.getSex());
        this.response = response;
        this.respCode = respCode;
        this.respMessage = respMessage;
    }

    public PatientOnkoCaseResponseRecord() {

    }
}
