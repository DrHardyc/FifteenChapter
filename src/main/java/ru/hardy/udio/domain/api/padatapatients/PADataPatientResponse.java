package ru.hardy.udio.domain.api.padatapatients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class PADataPatientResponse extends APIResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<PADataPatientResponseRecord> patients;

    @OneToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private PADataPatientRequest request;
}
