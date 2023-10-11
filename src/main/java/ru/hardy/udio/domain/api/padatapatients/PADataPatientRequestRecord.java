package ru.hardy.udio.domain.api.padatapatients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponseRecord;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
@EqualsAndHashCode(callSuper = true)
public class PADataPatientRequestRecord extends InsuredPerson {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private PADataPatientRequest request;

    @JsonIgnore
    @OneToOne(mappedBy = "requestRecord")
    private PADataPatient patient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ihi_id")
    private IndividualHistoryInforming individualHistoryInforming;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ihiResponseRecord_id")
    private IndividualHistoryInformingResponseRecord ihiResponseRecord;

    private String mainDiagnosis;
    private String concomitantDiagnosis;
    private String diagnosisComplications;
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

    public PADataPatientRequestRecord() {

    }

    public LocalDate getDateIncludeLocalDate() {
        return dateToLocalDate(this.dateInclude);
    }

    public LocalDate getDateInsuranceCaseLocalDate() {
        return dateToLocalDate(this.dateInsuranceCase);
    }


    private LocalDate dateToLocalDate(Date date){
        if (date != null) {
            return Instant.ofEpochMilli(date.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
        return null;
    }

}
