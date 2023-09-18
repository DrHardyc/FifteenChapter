package ru.hardy.udio.domain.api.dodatapatients;

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
public class DODataPatientsRequestRecord extends InsuredPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    @JsonIgnore
    private DODataPatientsRequest request;

    private String mainDiagnosis;
    private Date dateInclude;
    private String periodDO;
    private int specialtyDoctorCode;
    private Date previousDate;
    private int scheduledMonthAdmission;
    private int locationInspection;
    private Date dateInsuranceCase;
    private String resultDispensaryAppointmentDoctor;
    private String resultDispensaryAppointment;

    @JsonIgnore
    private Date date_beg;
    @JsonIgnore
    private Date date_edit;
}
