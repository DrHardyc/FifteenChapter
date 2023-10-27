package ru.hardy.udio.domain.api.volumemedicalcare;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.APIDepartmentResponseRecord;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class VolumeMedicalCareResponseRecord extends APIDepartmentResponseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private VolumeMedicalCareResponse response;

    public VolumeMedicalCareResponseRecord(VolumeMedicalCareRequestRecord departmentRequest,
                                           VolumeMedicalCareResponse volumeMedicalCareResponse,
                                           int errCode, String errMess) {
        this.setResponse(volumeMedicalCareResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setCodeDep(departmentRequest.getCodeDep());
        this.setNameDep(departmentRequest.getNameDep());
        this.setRespCode(errCode);
        this.setRespMessage(errMess);

    }

    public VolumeMedicalCareResponseRecord() {

    }
}
