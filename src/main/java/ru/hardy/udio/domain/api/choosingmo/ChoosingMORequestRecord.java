package ru.hardy.udio.domain.api.choosingmo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class ChoosingMORequestRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private ChoosingMORequest request;

    @OneToOne(mappedBy = "requestRecord")
    private ChoosingMO patient;
}
