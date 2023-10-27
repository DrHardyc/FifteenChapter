package ru.hardy.udio.domain.api.hospitalization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.atmosphere.config.service.Get;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_tfoms")
public class Hospitalization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private People people;

    @OneToOne(fetch = FetchType.LAZY)
    private HospitalizationRequestRecordPatient patient;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;
}
