package ru.hardy.udio.domain.api.numberavailableseats;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeatsRequestRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int code;
    private String name;
    private int numberPlacesCurrentDay;
    private int NumberPlacesNext10Days;
    private Date earliestReleaseDate;
    private int numberPlacesAvailableSoon;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private NumberAvailableSeatsRequest request;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;
}
