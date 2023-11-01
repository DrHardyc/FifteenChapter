package ru.hardy.udio.domain.api.schedulepianddispplot;

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

    public String getNameByCodeTypePreventiveActions(){
        return switch (this.getCodeTypePreventiveActions()) {
            case 0 -> "Диспансеризация";
            case 1 -> "Первый этап диспансеризации";
            case 2 -> "Второй этап диспансеризации";
            case 3 -> "Д-наблюдение";
            case 4 -> "Профосмотр";
            default -> "Код не опознан";
        };
    }

}
