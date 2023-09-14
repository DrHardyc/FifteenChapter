package ru.hardy.udio.domain.api.individualhistoryonkocase;

import lombok.Data;

import java.util.Date;

@Data
public class IndividualHistoryOnkoCaseResponseRecord {
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

    public IndividualHistoryOnkoCaseResponseRecord(String invoiceNumber, Date invoiceDate, String insuranceCaseName,
                                                   Date treatmentStartDate, Date treatmentEndDate, String mainDiagnosis,
                                                   String concomitantDiagnosis, String complicationsDiagnosis,
                                                   String resultSeeking, String informationDO) {
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
