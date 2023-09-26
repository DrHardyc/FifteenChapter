package ru.hardy.udio.domain.api.schedulepianddispplot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class MonthEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "requestRecord_id", nullable = false)
    @JsonIgnore
    private SchedulePIAndDispPlotRequestRecord requestRecord;

    public int month;
    public int quantityPlan;
    public int quantityInDepth;
    public int quantityPrevDept;
    public int quantityCE;
}
