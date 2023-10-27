package ru.hardy.udio.domain.api.operatingschedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.Department;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class OperatingScheduleRequestRecord extends Department {

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

    public String scheduleDep;
    public String holidaysDep;
    public String address;
}
