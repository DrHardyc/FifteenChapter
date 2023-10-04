package ru.hardy.udio.domain.api.individualinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualInformingRequestRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private IndividualInformingRequest request;

    @OneToOne(mappedBy = "requestRecord")
    private IndividualInforming patient;

    private Date dateNotification;
    private int sequenceInformation;
    private String wayInforming;
    private String mainDiagnosis;
    private String concomitantDiagnosis;
    private Date plannedNotificationDate;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;
}
