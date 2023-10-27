package ru.hardy.udio.domain.api.individualinforming;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIRequest;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualInformingRequest extends APIRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<IndividualInformingRequestRecord> patients;
}
