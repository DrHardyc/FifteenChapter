package ru.hardy.udio.domain.api.schedulepianddispplot;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequest;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class SchedulePIAndDispPlotResponse extends APIResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<SchedulePIAndDispPlotResponseRecord> departments;

    @OneToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private SchedulePIAndDispPlotRequest request;
}
