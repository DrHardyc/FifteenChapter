package ru.hardy.udio.domain.api.numberavailableseats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_tfoms")
public class NumberAvailableSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private NumberAvailableSeatsRequestRecord requestRecord;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

}
