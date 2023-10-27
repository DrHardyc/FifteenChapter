package ru.hardy.udio.domain.api.individualhistoryonkocase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.nsi.MedicalOrganization;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryOnkoCaseRequest extends APIRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "mo_id")
    private MedicalOrganization medicalOrganization;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IndividualHistoryOnkoCaseRequestRecord> patients;
}
