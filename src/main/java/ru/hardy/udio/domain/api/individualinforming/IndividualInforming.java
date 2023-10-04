package ru.hardy.udio.domain.api.individualinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_tfoms")
public class IndividualInforming {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int codeMO;

    @ManyToOne
    @JoinColumn(name = "people_id", nullable = false)
    @JsonIgnore
    private People people;

    @OneToOne(fetch = FetchType.LAZY)
    private IndividualInformingRequestRecord requestRecord;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

}
