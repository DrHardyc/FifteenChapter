package ru.hardy.udio.domain.api.recommendationspatient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_tfoms")
public class RecommendationsPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    @JsonIgnore
    private People people;

    @OneToOne(fetch = FetchType.LAZY)
    private RecommendationsPatientRequestRecord requestRecord;
    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;


    public RecommendationsPatient(){

    }
}
