package ru.hardy.udio.domain.api.schedulepianddispplot.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.MonthEvent;
import ru.hardy.udio.domain.api.schedulepianddispplot.mo.SchedulePIAndDispPlotRequestRecord;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class MonthEventSMO {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public int month;
    public int quantityPlan;
    public int codeTypePreventiveActions;

    @JsonIgnore
    public Date dateBeg;
    @JsonIgnore
    public Date dateEdit;

    public MonthEventSMO(MonthEvent monthEvent) {
        this.setMonth(monthEvent.getMonth());
        //this.setResponseRecord(responseRecord);
        this.setCodeTypePreventiveActions(monthEvent.getCodeTypePreventiveActions());
        this.setQuantityPlan(monthEvent.getQuantityPlan());
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
    }

    public MonthEventSMO() {

    }
}
