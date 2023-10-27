package ru.hardy.udio.domain.api.volumemedicalcare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.Diagnosis;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class VolumeMedicalCareDiagnosis extends Diagnosis {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestRecord_id", nullable = false)
    @JsonIgnore
    private VolumeMedicalCareRequestRecord requestRecord;

    private int quantity;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;
}
