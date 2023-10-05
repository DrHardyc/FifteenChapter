package ru.hardy.udio.domain.api.individualhistoryinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema ="udio_datacontrol")
public class IndividualHistoryInformingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reqID;
    private int codeMO;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IndividualHistoryInformingRequestRecord> patients;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

}
