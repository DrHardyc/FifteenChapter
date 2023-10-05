package ru.hardy.udio.domain.api.individualhistoryinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryInforming {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "ihiResponseRecord", fetch = FetchType.LAZY)
    private List<IndividualInformingRequestRecord> individualInformings;

    @OneToMany(mappedBy = "ihiResponseRecord", fetch = FetchType.LAZY)
    private List<PADataPatientRequestRecord> paDataPatients;

    @OneToOne(fetch = FetchType.LAZY)
    private People people;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;


    public IndividualHistoryInforming(People people,
                                      List<IndividualInformingRequestRecord> individualInformings,
                                      List<PADataPatientRequestRecord> paDataPatients) {

        if (individualInformings != null){
            this.setIndividualInformings(individualInformings);
        }
        if (paDataPatients != null){
            this.setPaDataPatients(paDataPatients);
        }
        this.setPeople(people);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }

    public IndividualHistoryInforming() {

    }
}
