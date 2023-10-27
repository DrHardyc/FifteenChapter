package ru.hardy.udio.domain.api.operatingschedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class OperatingScheduleResponse extends APIResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OperatingScheduleResponseRecord> departments;

    @OneToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private OperatingScheduleRequest request;
}
