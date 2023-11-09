package ru.hardy.udio.domain.api.numberavailableseats.mo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.Department;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeatsRequestRecord extends Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "departmentRequest_id")
    private List<DateNumberVacantPlaces> dateNumberVacantPlaces;

    private int numberPlacesCurrentDay;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private NumberAvailableSeatsRequest request;

    @OneToOne(mappedBy = "requestRecord", cascade = CascadeType.ALL)
    private NumberAvailableSeats department;

}
