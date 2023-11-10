package ru.hardy.udio.domain.api.recommendationspatient.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.recommendationspatient.mo.Participant;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class ParticipantSMO {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String surname;
    private String name;
    private String patronymic;
    private String jobTitle;

    @JsonIgnore
    public Date dateBeg;
    @JsonIgnore
    public Date dateEdit;

    public ParticipantSMO(Participant participant) {
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setSurname(participant.getSurname());
        this.setName(participant.getName());
        this.setPatronymic(participant.getPatronymic());
        this.setJobTitle(participant.getJobTitle());
    }

    public ParticipantSMO() {

    }
}
