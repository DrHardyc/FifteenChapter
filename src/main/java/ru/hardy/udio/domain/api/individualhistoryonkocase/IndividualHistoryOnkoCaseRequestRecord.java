package ru.hardy.udio.domain.api.individualhistoryonkocase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryOnkoCaseRequestRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private IndividualHistoryOnkoCaseRequest request;

    @OneToOne(fetch = FetchType.LAZY)
    private People people;


    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;
}
