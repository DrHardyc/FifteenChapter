package ru.hardy.udio.domain.api.padatapatients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class PADataPatientRequestRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private PADataPatientRequest request;

    @OneToOne(mappedBy = "requestRecord")
    private PADataPatient patient;

    @ManyToOne
    @JoinColumn(name = "ihi_id", nullable = false)
    @JsonIgnore
    private IndividualHistoryInforming individualHistoryInforming;

    private String mainDiagnosis;
    private String concomitantDiagnosis;
    private int codeTypePreventiveActions;
    private String nameTypePreventiveActions;
    private Date dateInclude;
    private String periodPA;
    private int specialtyDoctorCode;
    private int scheduledMonthAdmission;
    private int locationInspection;
    private Date dateInsuranceCase;
    private int resultDispensaryAppointmentDoctor;
    private int resultDispensaryAppointment;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;
}
