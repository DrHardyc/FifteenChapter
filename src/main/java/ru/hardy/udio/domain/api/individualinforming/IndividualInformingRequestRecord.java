package ru.hardy.udio.domain.api.individualinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponseRecord;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualInformingRequestRecord extends InsuredPerson {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private IndividualInformingRequest request;

    @JsonIgnore
    @OneToOne(mappedBy = "requestRecord")
    private IndividualInforming patient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ihi_id")
    private IndividualHistoryInforming individualHistoryInforming;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ihiResponseRecord_id")
    private IndividualHistoryInformingResponseRecord ihiResponseRecord;

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



    public IndividualInformingRequestRecord() {

    }
}
