package ru.hardy.udio.domain.api.numberavailableseats;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class NumberAvailableSeatsResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private int resultRequestCode;
    private String reqID;
    private int codeMO;
    private int numberRecordsProcessed;

    @OneToMany(mappedBy = "response", fetch = FetchType.LAZY)
    private List<NumberAvailableSeatsResponseRecord> departmentResponse;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;


    public NumberAvailableSeatsResponse() {

    }

}
