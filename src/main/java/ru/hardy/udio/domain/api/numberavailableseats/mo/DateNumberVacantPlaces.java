package ru.hardy.udio.domain.api.numberavailableseats.mo;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class DateNumberVacantPlaces {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date earliestReleaseDate;
    private int numberPlacesAvailableSoon;
}
