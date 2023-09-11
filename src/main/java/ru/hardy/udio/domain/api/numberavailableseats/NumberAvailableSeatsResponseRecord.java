package ru.hardy.udio.domain.api.numberavailableseats;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.patientonkocase.PatientOnkoCaseResponse;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeatsResponseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int code;
    private String name;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private NumberAvailableSeatsResponse response;

    private int respCode;
    private String respMessage;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

    public NumberAvailableSeatsResponseRecord(NumberAvailableSeatsRequestRecord department,
                                              NumberAvailableSeatsResponse numberAvailableSeatsResponse,
                                              int errCode, String errMess) {

        this.setResponse(numberAvailableSeatsResponse);
        this.setCode(department.getCode());
        this.setName(department.getName());
        this.setDate_beg(Date.from(Instant.now()));
        this.setDate_edit(Date.from(Instant.now()));
        this.response = numberAvailableSeatsResponse;
        this.respCode = errCode;
        this.respMessage = errMess;

    }

    public NumberAvailableSeatsResponseRecord() {

    }
}
