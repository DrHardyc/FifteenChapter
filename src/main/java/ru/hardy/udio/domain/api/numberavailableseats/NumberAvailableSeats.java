package ru.hardy.udio.domain.api.numberavailableseats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.Department;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_tfoms")
public class NumberAvailableSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int codeMO;
    private int codeDep;
    private String nameDep;

    @OneToOne(fetch = FetchType.LAZY)
    private NumberAvailableSeatsRequestRecord departmentRequest;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

}
