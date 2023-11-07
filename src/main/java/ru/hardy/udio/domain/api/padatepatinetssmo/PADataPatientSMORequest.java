package ru.hardy.udio.domain.api.padatepatinetssmo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIRequest;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class PADataPatientSMORequest extends APIRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int codeTypePreventiveActions;
    private int statusTypePreventiveActions;
}
