package ru.hardy.udio.domain.api.hospitalization.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.hospitalization.mo.Hospitalization;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class HospitalizationPatientDTO extends InsuredPerson {
    private int codeHospitalization;
    private String reasonNotHospitalization;
    private Date dateEvent;
    private String mainDiagnosis;
    private int codeMO;
    private int codeDep;
    private String nameDep;

    public HospitalizationPatientDTO(Hospitalization hospitalization) {
        this.setCodeHospitalization(hospitalization.getPatient().getCodeHospitalization());
        this.setCodeMO(hospitalization.getPatient().getRequestRecord().getRequest().getMedicalOrganization().getCodeMO());
        this.setReasonNotHospitalization(hospitalization.getPatient().getReasonNotHospitalization());
        this.setDateEvent(hospitalization.getPatient().getDateEvent());
        this.setMainDiagnosis(hospitalization.getPatient().getMainDiagnosis());
        this.setCodeDep(hospitalization.getPatient().getRequestRecord().getCodeDep());
        this.setNameDep(hospitalization.getPatient().getRequestRecord().getNameDep());
        this.setSurname(hospitalization.getPatient().getSurname());
        this.setName(hospitalization.getPatient().getName());
        this.setPatronymic(hospitalization.getPatient().getPatronymic());
        this.setDateBeg(hospitalization.getPatient().getDateBirth());
        this.setEnp(hospitalization.getPatient().getEnp());
    }
}
