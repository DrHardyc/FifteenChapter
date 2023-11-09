package ru.hardy.udio.domain.api.schedulepianddispplot.smo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class SchedulePIAndDispPlotSMOResponse extends APIResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SchedulePIAndDispPlotSMOResponseRecord> departments;

    @OneToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private SchedulePIAndDispPlotSMORequest request;

}
