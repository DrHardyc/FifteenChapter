package ru.hardy.udio.domain.api.recommendationspatient.mo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class Participant {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestRecord_id", nullable = false)
    @JsonIgnore
    private RecommendationsPatientRequestRecord requestRecord;

    private String surname;
    private String name;
    private String patronymic;
    private String jobTitle;

    @JsonIgnore
    public Date dateBeg;
    @JsonIgnore
    public Date dateEdit;

}
