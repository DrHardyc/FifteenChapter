package ru.hardy.udio.domain.api.operatingschedule;

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
public class OperatingScheduleResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String reqID;
    private int codeMO;
    private int resultRequestCode;
    private int numberRecordsProcessed;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OperatingScheduleResponseRecord> departments;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;


}
