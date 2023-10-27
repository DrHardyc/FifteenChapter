package ru.hardy.udio.domain.api.individualhistoryonkocase;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponseRecord;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryOnkoCaseResponse extends APIResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<IndividualHistoryOnkoCaseResponseRecord> patients;

    @OneToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private IndividualHistoryOnkoCaseRequest request;

    public IndividualHistoryOnkoCaseResponse(){

    }
}
