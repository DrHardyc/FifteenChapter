package ru.hardy.udio.domain.api.numberavailableseats;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private NumberAvailableSeatsRequest request;

    private int codeMO;
    private int codeDep;
    private String nameDep;
    private int numberPlacesCurrentDay;
    private int numberPlacesNext10Days;
    private Date earliestReleaseDate;
    private int numberPlacesAvailableSoon;

    private Date date_beg;
    private Date date_edit;

}
