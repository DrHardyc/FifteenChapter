package ru.hardy.udio.domain.api.operatingschedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class OperatingScheduleResponseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private OperatingScheduleResponse response;

    private int codeDep;
    private String nameDep;
    private int respCode;
    private String respMessage;

    @JsonIgnore
    private Date dateBeg;

    @JsonIgnore
    private Date dateEdit;

    public OperatingScheduleResponseRecord(OperatingScheduleRequestRecord departmentRequest,
                                           OperatingScheduleResponse operatingScheduleResponse,
                                           int errCode, String errMess) {
        this.setResponse(operatingScheduleResponse);
        this.setCodeDep(operatingScheduleResponse.getCodeMO());
        this.setCodeDep(departmentRequest.getCodeDep());
        this.setNameDep(departmentRequest.getNameDep());
        this.setRespCode(errCode);
        this.setRespMessage(errMess);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }
}























