package ru.hardy.udio.domain.api.individualhistoryonkocase;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.io.Serial;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryOnkoCaseRequest extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateBeg;
    private Date dateEdit;
}
