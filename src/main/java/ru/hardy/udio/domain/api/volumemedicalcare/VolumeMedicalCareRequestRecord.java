package ru.hardy.udio.domain.api.volumemedicalcare;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.Department;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class VolumeMedicalCareRequestRecord extends Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private VolumeMedicalCareRequest request;

    @OneToMany(mappedBy = "requestRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VolumeMedicalCareDiagnosis> diagnoses;

    @OneToOne(mappedBy = "requestRecord")
    private VolumeMedicalCare department;

}
