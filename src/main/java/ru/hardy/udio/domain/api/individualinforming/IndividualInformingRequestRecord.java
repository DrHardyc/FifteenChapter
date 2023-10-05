package ru.hardy.udio.domain.api.individualinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;

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

    @ManyToOne
    @JoinColumn(name = "ihiResponseRecord_id")
    @JsonIgnore
    private IndividualHistoryInforming ihiResponseRecord;

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
