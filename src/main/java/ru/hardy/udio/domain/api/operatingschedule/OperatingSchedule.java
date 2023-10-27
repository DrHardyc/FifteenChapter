package ru.hardy.udio.domain.api.operatingschedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_tfoms")
public class OperatingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private OperatingScheduleRequestRecord requestRecord;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;
}
