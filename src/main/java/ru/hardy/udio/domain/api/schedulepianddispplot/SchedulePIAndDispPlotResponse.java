package ru.hardy.udio.domain.api.schedulepianddispplot;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class SchedulePIAndDispPlotResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String reqID;
    private int codeMO;
    private int resultRequestCode;
    private int numberRecordsProcessed;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<SchedulePIAndDispPlotResponseRecord> departmentsResponse;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;
}
