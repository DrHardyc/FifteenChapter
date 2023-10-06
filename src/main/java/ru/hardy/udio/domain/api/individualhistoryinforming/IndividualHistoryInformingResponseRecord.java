package ru.hardy.udio.domain.api.individualhistoryinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryInformingResponseRecord extends InsuredPerson {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int respCode;
    private String respMessage;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private IndividualHistoryInformingResponse response;

    @OneToMany(mappedBy = "ihiResponseRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IndividualInformingRequestRecord> IndividualInforming;

    @OneToMany(mappedBy = "ihiResponseRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PADataPatientRequestRecord> insuranceCase;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

    public IndividualHistoryInformingResponseRecord(IndividualHistoryInformingRequestRecord individualHistoryInformingRequestRecord,
                                                    IndividualHistoryInformingResponse individualHistoryInformingResponse,
                                                    List<IndividualInformingRequestRecord> IndividualInforming,
                                                    List<PADataPatientRequestRecord> insuranceCase,
                                                    int respCode, String respMessage){
        this.setSurname(individualHistoryInformingRequestRecord.getSurname());
        this.setName(individualHistoryInformingRequestRecord.getName());
        this.setDateBirth(individualHistoryInformingRequestRecord.getDateBirth());
        this.setPatronymic(individualHistoryInformingRequestRecord.getPatronymic());
        this.setEnp(individualHistoryInformingRequestRecord.getEnp());
        this.setResponse(individualHistoryInformingResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setIndividualInforming(IndividualInforming);
        this.setInsuranceCase(insuranceCase);
        this.setRespCode(respCode);
        this.setRespMessage(respMessage);
    }


    public IndividualHistoryInformingResponseRecord() {

    }
}
