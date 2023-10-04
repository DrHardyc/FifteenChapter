package ru.hardy.udio.domain.api.operatingschedule;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class OperatingScheduleRequestRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private OperatingScheduleRequest request;

    @OneToMany(mappedBy = "requestRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cabinet> cabinets;

    @OneToOne(mappedBy = "requestRecord")
    private OperatingSchedule department;

    public int codeDep;
    public String nameDep;
    public String scheduleDep;
    public String holidaysDep;
    public String address;

    private Date date_beg;
    private Date date_edit;
}
