package ru.hardy.udio.domain.api.padatapatients.smo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class PADataPatientSMOResponseRecord extends InsuredPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "response_id", nullable = false)
    @JsonIgnore
    private PADataPatientSMOResponse response;

    private int codeMO;
    private String city;
    private String street;
    private String house;
    private String apartment;
    private String mainDiagnosis;
    private int firstIdentified;
    private String concomitantDiagnosis;
    private String diagnosisComplications;
    private int codeTypePreventiveActions;
    private int statusTypePreventiveActions;
    private String nameTypePreventiveActions;
    private Date dateInclude;
    private String periodPA;
    private int specialtyDoctorCode;
    private int scheduledMonthAdmission;
    private int locationInspection;
    private Date dateInsuranceCase;
    private int resultDispensaryAppointmentDoctor;
    private int resultDispensaryAppointment;

    public PADataPatientSMOResponseRecord(PADataPatientRequestRecord paDataPatientRequestRecord, PADataPatientSMOResponse paDataPatientSMOResponse) {
        this.setCodeMO(paDataPatientSMOResponse.getCodeMO());
        this.setResponse(paDataPatientSMOResponse);
        this.setSurname(paDataPatientRequestRecord.getSurname());
        this.setName(paDataPatientRequestRecord.getName());
        this.setPatronymic(paDataPatientRequestRecord.getPatronymic());
        this.setDateBirth(paDataPatientRequestRecord.getDateBirth());
        this.setEnp(paDataPatientRequestRecord.getEnp());
        this.setCity(paDataPatientRequestRecord.getCity());
        this.setStreet(paDataPatientRequestRecord.getStreet());
        this.setHouse(paDataPatientRequestRecord.getHouse());
        this.setApartment(paDataPatientRequestRecord.getApartment());
        this.setMainDiagnosis(paDataPatientRequestRecord.getMainDiagnosis());
        this.setFirstIdentified(paDataPatientRequestRecord.getFirstIdentified());
        this.setConcomitantDiagnosis(paDataPatientRequestRecord.getConcomitantDiagnosis());
        this.setDiagnosisComplications(paDataPatientRequestRecord.getDiagnosisComplications());
        this.setCodeTypePreventiveActions(paDataPatientRequestRecord.getCodeTypePreventiveActions());
        this.setStatusTypePreventiveActions(paDataPatientRequestRecord.getStatusTypePreventiveActions());
        this.setNameTypePreventiveActions(paDataPatientRequestRecord.getNameTypePreventiveActions());
        this.setDateInclude(paDataPatientRequestRecord.getDateInclude());
        this.setPeriodPA(paDataPatientRequestRecord.getPeriodPA());
        this.setSpecialtyDoctorCode(paDataPatientRequestRecord.getSpecialtyDoctorCode());
        this.setScheduledMonthAdmission(paDataPatientRequestRecord.getScheduledMonthAdmission());
        this.setLocationInspection(paDataPatientRequestRecord.getLocationInspection());
        this.setDateInsuranceCase(paDataPatientRequestRecord.getDateInsuranceCase());
        this.setResultDispensaryAppointmentDoctor(paDataPatientRequestRecord.getResultDispensaryAppointmentDoctor());
        this.setResultDispensaryAppointment(paDataPatientRequestRecord.getResultDispensaryAppointment());
    }

    public PADataPatientSMOResponseRecord() {

    }
}

