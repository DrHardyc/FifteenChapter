package ru.hardy.udio.domain.api.schedulepianddispplot.mo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class MonthEvent {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestRecord_id", nullable = false)
    @JsonIgnore
    private SchedulePIAndDispPlotRequestRecord requestRecord;

    public int month;
    public int quantityPlan;
    public int codeTypePreventiveActions;

    @JsonIgnore
    public Date dateBeg;
    @JsonIgnore
    public Date dateEdit;
}
