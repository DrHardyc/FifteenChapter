package ru.hardy.udio.domain.api.individualhistoryinforming;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class IndividualHistoryInformingResponse{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String reqID;
    private int codeMO;
    private int resultRequestCode;
    private int numberRecordsProcessed;


    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<IndividualHistoryInformingResponseRecord> patients;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

    public IndividualHistoryInformingResponse() {

    }
}
