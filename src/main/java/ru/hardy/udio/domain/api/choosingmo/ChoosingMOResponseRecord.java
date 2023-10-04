package ru.hardy.udio.domain.api.choosingmo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class ChoosingMOResponseRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private ChoosingMOResponse response;

    private int respCode;
    private String respMessage;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

    public ChoosingMOResponseRecord (ChoosingMORequestRecord patient, ChoosingMOResponse response,
                                     int respCode, String respMessage) {
        this.setSurname(patient.getSurname());
        this.setName(patient.getName());
        this.setPatronymic(patient.getPatronymic());
        this.setEnp(patient.getEnp());
        this.setDateBirth(patient.getDateBirth());
        this.response = response;
        this.respCode = respCode;
        this.respMessage = respMessage;
        this.date_beg = Date.from(Instant.now());
        this.date_edit = Date.from(Instant.now());
    }

    public ChoosingMOResponseRecord() {

    }
}
