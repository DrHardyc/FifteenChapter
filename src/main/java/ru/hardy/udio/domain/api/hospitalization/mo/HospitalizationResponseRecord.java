package ru.hardy.udio.domain.api.hospitalization.mo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.Department;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema ="udio_datacontrol")
public class HospitalizationResponseRecord extends Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @OneToMany(mappedBy = "responseRecord", fetch = FetchType.LAZY)
    private List<HospitalizationResponseRecordPatient> patients;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private HospitalizationResponse response;

    public HospitalizationResponseRecord(){

    }

    public HospitalizationResponseRecord(HospitalizationRequestRecord hospitalizationRequestRecord,
                                         HospitalizationResponse hospitalizationResponse,
                                         List<HospitalizationResponseRecordPatient> hospitalizationResponseRecordPatients) {
        this.setResponse(hospitalizationResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setCodeDep(hospitalizationRequestRecord.getCodeDep());
        this.setNameDep(hospitalizationRequestRecord.getNameDep());
        hospitalizationResponseRecordPatients.forEach(patient -> {
            patient.setResponseRecord(this);
        });
        this.setPatients(hospitalizationResponseRecordPatients);
    }
}
