package ru.hardy.udio.domain.api.padatapatients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.abstractclasses.InsuredPerson;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class PADataPatientsRequestRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private PADataPatientsRequest request;

    @OneToOne(mappedBy = "requestRecord")
    private PADataPatient patient;

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
