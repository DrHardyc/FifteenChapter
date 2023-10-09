package ru.hardy.udio.domain.api.individualhistoryonkocase;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(schema = "udio_datacontrol")
public class InsuranceCase {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "responseRecord_id", nullable = false)
    @JsonIgnore
    private IndividualHistoryOnkoCaseResponseRecord responseRecord;

    private int codeMOAttach;
    private int codeMO;
    private String contactDetails;
    private String invoiceNumber;
    private Date invoiceDate;
    private String insuranceCaseName;
    private Date treatmentStartDate;
    private Date treatmentEndDate;
    private String mainDiagnosis;
    private String concomitantDiagnosis;
    private String complicationsDiagnosis;
    private String resultSeeking;
    private String informationDO;

    @JsonIgnore
    private Date dateBeg;
    @JsonIgnore
    private Date dateEdit;

    public InsuranceCase(int codeMOAttach, int codeMO, String contactDetails, String invoiceNumber,
                         Date invoiceDate, String insuranceCaseName, Date treatmentStartDate, Date treatmentEndDate,
                         String mainDiagnosis, String concomitantDiagnosis, String complicationsDiagnosis,
                         String resultSeeking, String informationDO) {

        this.setCodeMOAttach(codeMOAttach);
        this.setCodeMO(codeMO);
        this.setContactDetails(contactDetails);
        this.setInvoiceNumber(invoiceNumber);
        this.setInvoiceDate(invoiceDate);
        this.setInsuranceCaseName(insuranceCaseName);
        this.setTreatmentStartDate(treatmentStartDate);
        this.setTreatmentEndDate(treatmentEndDate);
        this.setMainDiagnosis(mainDiagnosis);
        this.setConcomitantDiagnosis(concomitantDiagnosis);
        this.setComplicationsDiagnosis(complicationsDiagnosis);
        this.setResultSeeking(resultSeeking);
        this.setInformationDO(informationDO);
    }
}
