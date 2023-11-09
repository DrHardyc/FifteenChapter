package ru.hardy.udio.domain.api.schedulepianddispplot.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIDepartmentResponseRecord;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotRequestRecord;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class SchedulePIAndDispPlotSMOResponseRecord extends APIDepartmentResponseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private SchedulePIAndDispPlotSMOResponse response;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "responsrecodesmo_id")
    private List<MonthEventSMO> months;

    public SchedulePIAndDispPlotSMOResponseRecord(SchedulePIAndDispPlotRequestRecord schedulePIAndDispPlotRequestRecord,
                                                  SchedulePIAndDispPlotSMOResponse schedulePIAndDispPlotSMOResponse,
                                                  List<MonthEventSMO> months) {
        this.setResponse(schedulePIAndDispPlotSMOResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setCodeDep(schedulePIAndDispPlotRequestRecord.getCodeDep());
        this.setNameDep(schedulePIAndDispPlotRequestRecord.getNameDep());
        this.setMonths(months);
    }

    public SchedulePIAndDispPlotSMOResponseRecord() {

    }
}
