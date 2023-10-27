package ru.hardy.udio.domain.api.volumemedicalcare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.SchedulePIAndDispPlotResponseRecord;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class VolumeMedicalCareResponse extends APIResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<VolumeMedicalCareResponseRecord> departments;

    @OneToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private VolumeMedicalCareRequest request;
}
