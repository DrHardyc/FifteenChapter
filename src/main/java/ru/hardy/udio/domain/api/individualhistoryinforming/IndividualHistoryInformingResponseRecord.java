package ru.hardy.udio.domain.api.individualhistoryinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryInformingResponseRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int respCode;
    private String respMessage;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private IndividualHistoryInformingResponse response;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "iiRequestRecord_id")
    private List<IndividualInformingRequestRecord> individualInformingRequestRecords;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "paRequestRecord_id")
    private List<PADataPatientRequestRecord> paDataPatientRequestRecords;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

    public IndividualHistoryInformingResponseRecord(IndividualHistoryInformingRequestRecord individualHistoryInformingRequestRecord,
                                                    IndividualHistoryInformingResponse individualHistoryInformingResponse,
                                                    List<IndividualInformingRequestRecord> individualInformingRequestRecords,
                                                    List<PADataPatientRequestRecord> paDataPatientRequestRecords,
                                                    int respCode, String respMessage){
        this.setSurname(individualHistoryInformingRequestRecord.getSurname());
        this.setName(individualHistoryInformingRequestRecord.getName());
        this.setDateBirth(individualHistoryInformingRequestRecord.getDateBirth());
        this.setPatronymic(individualHistoryInformingRequestRecord.getPatronymic());
        this.setEnp(individualHistoryInformingRequestRecord.getEnp());
        this.setResponse(individualHistoryInformingResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setIndividualInformingRequestRecords(individualInformingRequestRecords);
        this.setPaDataPatientRequestRecords(paDataPatientRequestRecords);
        this.setRespCode(respCode);
        this.setRespMessage(respMessage);
    }


    public IndividualHistoryInformingResponseRecord() {

    }
}
