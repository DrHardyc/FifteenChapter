package ru.hardy.udio.domain.api.numberavailableseats;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeatsResponse extends APIResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<NumberAvailableSeatsResponseRecord> departmentResponse;

    @OneToOne
    @JoinColumn(name = "request_id")
    @JsonIgnore
    private NumberAvailableSeatsRequest request;
    public NumberAvailableSeatsResponse() {

    }
}
