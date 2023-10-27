package ru.hardy.udio.domain.api.choosingmo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIRequest;

import java.util.List;


@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class ChoosingMORequest extends APIRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChoosingMORequestRecord> patients;
}
