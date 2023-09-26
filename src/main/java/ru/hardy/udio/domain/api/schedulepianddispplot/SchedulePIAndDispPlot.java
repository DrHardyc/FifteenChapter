package ru.hardy.udio.domain.api.schedulepianddispplot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_tfoms")
public class SchedulePIAndDispPlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int codeMO;

    @OneToOne(fetch = FetchType.LAZY)
    private SchedulePIAndDispPlotRequestRecord departmentRequest;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

}
