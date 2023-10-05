package ru.hardy.udio.domain.api.numberavailableseats;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.Department;


import java.util.Date;
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

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private NumberAvailableSeatsRequest request;

    @OneToOne(mappedBy = "requestRecord")
    private NumberAvailableSeats department;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;
}
