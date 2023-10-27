package ru.hardy.udio.domain.api.operatingschedule;

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
public class OperatingScheduleResponseRecord extends APIDepartmentResponseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private OperatingScheduleResponse response;

    public OperatingScheduleResponseRecord(OperatingScheduleRequestRecord departmentRequest,
                                           OperatingScheduleResponse operatingScheduleResponse,
                                           int errCode, String errMess) {
        this.setResponse(operatingScheduleResponse);
        this.setCodeDep(departmentRequest.getCodeDep());
        this.setNameDep(departmentRequest.getNameDep());
        this.setRespCode(errCode);
        this.setRespMessage(errMess);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }

    public OperatingScheduleResponseRecord() {

    }
}























