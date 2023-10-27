package ru.hardy.udio.domain.api.hospitalization;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.Department;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema ="udio_datacontrol")
public class HospitalizationRequestRecord extends Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private HospitalizationRequest request;

    @OneToMany(mappedBy = "requestRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HospitalizationRequestRecordPatient> patients;
}
