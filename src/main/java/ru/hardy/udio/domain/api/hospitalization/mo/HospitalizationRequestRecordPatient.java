package ru.hardy.udio.domain.api.hospitalization.mo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class HospitalizationRequestRecordPatient extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestRecord_id", nullable = false)
    private HospitalizationRequestRecord requestRecord;

    private Date dateEvent;
    private String mainDiagnosis;
    private int codeHospitalization;
    private String reasonNotHospitalization;

}
