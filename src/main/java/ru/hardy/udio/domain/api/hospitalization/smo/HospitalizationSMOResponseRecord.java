package ru.hardy.udio.domain.api.hospitalization.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.hospitalization.dto.HospitalizationPatientDTO;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class HospitalizationSMOResponseRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private HospitalizationSMOResponse response;

    private int codeHospitalization;
    private String reasonNotHospitalization;
    private Date dateEvent;
    private String mainDiagnosis;
    private int codeMO;
    private int codeDep;
    private String nameDep;

    public HospitalizationSMOResponseRecord(HospitalizationPatientDTO hospitalizationPatientDTO, HospitalizationSMOResponse hospitalizationSMOResponse) {
        this.setResponse(hospitalizationSMOResponse);
        this.setDateBeg(Date.from(Instant.now()));
        this.setDateEdit(Date.from(Instant.now()));
        this.setCodeHospitalization(hospitalizationPatientDTO.getCodeHospitalization());
        this.setReasonNotHospitalization(hospitalizationPatientDTO.getReasonNotHospitalization());
        this.setDateEdit(hospitalizationPatientDTO.getDateEvent());
        this.setMainDiagnosis(hospitalizationPatientDTO.getMainDiagnosis());
        this.setCodeMO(hospitalizationPatientDTO.getCodeMO());
        this.setCodeDep(hospitalizationPatientDTO.getCodeDep());
        this.setNameDep(hospitalizationPatientDTO.getNameDep());
        this.setSurname(hospitalizationPatientDTO.getSurname());
        this.setName(hospitalizationPatientDTO.getName());
        this.setPatronymic(hospitalizationPatientDTO.getPatronymic());
        this.setDateBirth(hospitalizationPatientDTO.getDateBirth());
        this.setEnp(hospitalizationPatientDTO.getEnp());


    }

    public HospitalizationSMOResponseRecord() {

    }
}
