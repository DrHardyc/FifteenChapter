package ru.hardy.udio.domain.api.individualhistoryinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIInsuredPersonResponseRecord;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryInformingResponseRecord extends APIInsuredPersonResponseRecord {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private IndividualHistoryInformingResponse response;

    @OneToMany(mappedBy = "ihiResponseRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord> IndividualInforming;

    @OneToMany(mappedBy = "ihiResponseRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PADataPatientRequestRecord> insuranceCase;

    public IndividualHistoryInformingResponseRecord(IndividualHistoryInformingRequestRecord individualHistoryInformingRequestRecord,
                                                    IndividualHistoryInformingResponse individualHistoryInformingResponse,
                                                    List<ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord> IndividualInforming,
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
