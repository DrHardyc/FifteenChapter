package ru.hardy.udio.domain.api.volumemedicalcare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponseRecord;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class VolumeMedicalCareResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String reqID;
    private int codeMO;
    private int resultRequestCode;
    private int numberRecordsProcessed;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<VolumeMedicalCareResponseRecord> departments;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;

}
