package ru.hardy.udio.domain.api.individualhistoryonkocase;

import lombok.Data;

import java.util.Date;

@Data
public class IndividualHistoryOnkoCaseResponseRecord {
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

    public IndividualHistoryOnkoCaseResponseRecord(int codeMOAttach, int codeMO, String contactDetails,
                                                   String invoiceNumber, Date invoiceDate, String insuranceCaseName,
                                                   Date treatmentStartDate, Date treatmentEndDate, String mainDiagnosis,
                                                   String concomitantDiagnosis, String complicationsDiagnosis,
                                                   String resultSeeking, String informationDO) {
        this.codeMO = codeMO;
        this.codeMOAttach = codeMOAttach;
        this.contactDetails = contactDetails;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.insuranceCaseName = insuranceCaseName;
        this.treatmentStartDate = treatmentStartDate;
        this.treatmentEndDate = treatmentEndDate;
        this.mainDiagnosis = mainDiagnosis;
        this.concomitantDiagnosis = concomitantDiagnosis;
        this.complicationsDiagnosis = complicationsDiagnosis;
        this.resultSeeking = resultSeeking;
        this.informationDO = informationDO;

    }

}
