package ru.hardy.udio.domain.api.hospitalization;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class HospitalizationRequest extends APIRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List< HospitalizationRequestRecord> departments;
}
