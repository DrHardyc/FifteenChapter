package ru.hardy.udio.domain.api.numberavailableseats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIDepartmentResponseRecord;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeatsResponseRecord extends APIDepartmentResponseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private NumberAvailableSeatsResponse response;


    public NumberAvailableSeatsResponseRecord(NumberAvailableSeatsRequestRecord departmentRequest,
                                              NumberAvailableSeatsResponse numberAvailableSeatsResponse,
                                              int errCode, String errMess) {
        this.setCodeDep(departmentRequest.getCodeDep());
        this.setNameDep(departmentRequest.getNameDep());
        this.setResponse(numberAvailableSeatsResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setResponse(numberAvailableSeatsResponse);
        this.setRespCode(errCode);
        this.setRespMessage(errMess);
    }

    public NumberAvailableSeatsResponseRecord() {

    }
}
