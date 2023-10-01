package ru.hardy.udio.domain.api.schedulepianddispplot;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class SchedulePIAndDispPlotRequestRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private SchedulePIAndDispPlotRequest request;

    @OneToMany(mappedBy = "requestRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MonthEvent> months;

    public int codeDep;
    public String nameDep;

    private Date date_beg;
    private Date date_edit;
}
