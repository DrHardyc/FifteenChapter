package ru.hardy.udio.domain.api.schedulepianddispplot;

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
public class SchedulePIAndDispPlotResponseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private SchedulePIAndDispPlotResponse response;

    private int departmentCode;
    private String departmentName;
    private int respCode;
    private String respMessage;

    @JsonIgnore
    private Date dateBeg;

    @JsonIgnore
    private Date dateEdit;


    public SchedulePIAndDispPlotResponseRecord(SchedulePIAndDispPlotRequestRecord departmentRequest,
                                               SchedulePIAndDispPlotResponse schedulePIAndDispPlotResponse,
                                               int errCode, String errMess) {
        this.setDepartmentCode(departmentRequest.getCodeDep());
        this.setDepartmentName(departmentRequest.getNameDep());
        this.setResponse(schedulePIAndDispPlotResponse);
        this.setRespCode(errCode);
        this.setRespMessage(errMess);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }

    public SchedulePIAndDispPlotResponseRecord() {

    }
}
