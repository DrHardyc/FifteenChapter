package ru.hardy.udio.domain.api.individualhistoryonkocase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIInsuredPersonResponseRecord;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryOnkoCaseResponseRecord extends APIInsuredPersonResponseRecord {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private IndividualHistoryOnkoCaseResponse response;

    @OneToMany(mappedBy = "responseRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InsuranceCase> insuranceCases;

    public IndividualHistoryOnkoCaseResponseRecord(IndividualHistoryOnkoCaseRequestRecord individualHistoryOnkoCaseRequestRecord,
                                                   IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse,
                                                   List<InsuranceCase> insuranceCases, int errCode, String errMess) {
        insuranceCases.forEach(insuranceCase -> {
            insuranceCase.setResponseRecord(this);
            insuranceCase.setDateBeg(Date.from(Instant.now()));
            insuranceCase.setDateEdit(Date.from(Instant.now()));
        });
        this.setSurname(individualHistoryOnkoCaseRequestRecord.getSurname());
        this.setName(individualHistoryOnkoCaseRequestRecord.getName());
        this.setPatronymic(individualHistoryOnkoCaseRequestRecord.getPatronymic());
        this.setDateBirth(individualHistoryOnkoCaseRequestRecord.getDateBirth());
        this.setEnp(individualHistoryOnkoCaseRequestRecord.getEnp());
        this.setResponse(individualHistoryOnkoCaseResponse);
        this.setInsuranceCases(insuranceCases);
        this.setRespCode(errCode);
        this.setRespMessage(errMess);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }

    public IndividualHistoryOnkoCaseResponseRecord() {

    }
}
