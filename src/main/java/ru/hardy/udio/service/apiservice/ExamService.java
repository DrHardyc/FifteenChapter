package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.api.ResultRequest;
import ru.hardy.udio.domain.api.abstractclasses.InsuredPerson;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatient;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.SexService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.*;

@Service
public class ExamService {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PADataPatientService paDataPatientsService;

    @Autowired
    private IndividualInformingService individualInformingService;

    public boolean checkMKB(String mkb){
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        try {
            ResultSet resultSet = statement.executeQuery("select count(*) from nsi_med.med_mkb10 mm where " +
                    "mkb_code = '" + mkb + "'");
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public ResultRequest checkInsuredPatient(PADataPatientRequestRecord paDataPatientRequestRecord){

        ResultRequest resultRequest = checkInsuredPatientMain(paDataPatientRequestRecord);
        int errCode = resultRequest.getResCode();
        String errMess = resultRequest.getResMess();
        if (!checkMKB(paDataPatientRequestRecord.getMainDiagnosis())) {
            if (errCode == 500) errMess = "Ошибка распознавания поля 'mainDiagnosis : ";
            else errMess = errMess + "|Ошибка распознавания поля 'mainDiagnosis : ";
            errCode = 504;
            errMess = errMess + paDataPatientRequestRecord.getMainDiagnosis();
        }
        if (!paDataPatientRequestRecord.getConcomitantDiagnosis().isEmpty()){
            if (!checkMKB(paDataPatientRequestRecord.getConcomitantDiagnosis())){
                if (errCode == 500) errMess = "Ошибка распознавания поля 'concomitantDiagnosis' : ";
                else errMess = errMess + "|Ошибка распознавания поля 'concomitantDiagnosis' : ";
                errCode = 504;
                errMess = errMess + paDataPatientRequestRecord.getConcomitantDiagnosis();
            }
        }
        if (!paDataPatientRequestRecord.getDiagnosisComplications().isEmpty()){
            if (!checkMKB(paDataPatientRequestRecord.getConcomitantDiagnosis())){
                if (errCode == 500) errMess = "Ошибка распознавания поля 'diagnosisComplications' : ";
                else errMess = errMess + "|Ошибка распознавания поля 'diagnosisComplications' : ";
                errCode = 504;
                errMess = errMess + paDataPatientRequestRecord.getDiagnosisComplications();
            }
        }

        if (errCode == 500) {
            People people = peopleService.search(paDataPatientRequestRecord);
            if (people != null) {
                if (paDataPatientsService.checkPatient(people, paDataPatientRequestRecord.getRequest().getCodeMO(),
                        paDataPatientRequestRecord.getMainDiagnosis(),
                        paDataPatientRequestRecord.getCodeTypePreventiveActions(),
                        paDataPatientRequestRecord.getDateInsuranceCase())) {
                    errCode = 502;
                    errMess = "Пациент c таким диагнозом ранее был добавлен для данной МО";
                } else {
                    PADataPatient paDataPatient = paDataPatientsService.searchPatient(
                            people, paDataPatientRequestRecord.getRequest().getCodeMO(),
                            paDataPatientRequestRecord.getMainDiagnosis(),
                            paDataPatientRequestRecord.getCodeTypePreventiveActions());
                    if (paDataPatient != null) {
                        errMess = "Запись успешно обновлена";
                        paDataPatient.setRequestRecord(paDataPatientRequestRecord);
                        paDataPatient.setDateEdit(Date.from(Instant.now()));
                        paDataPatientsService.update(paDataPatient);
                    } else {
                        errMess = "Запись успешно добавлена";
                        paDataPatientsService.add(people, paDataPatientRequestRecord);
                    }
                }
            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }
        }
        resultRequest.setResMess(errMess);
        resultRequest.setResCode(errCode);
        return resultRequest;
    }

    private ResultRequest checkInsuredPatientMain(InsuredPerson insuredPerson) {
        int errCode = 500;
        String errMess = "Успешное выполнение обработки записи";
        ResultRequest resultRequest = new ResultRequest();
        if (checkEmptyString(insuredPerson.getSurname())) {
            errCode = 504;
            errMess = "|Ошибка распознавания поля 'surname'|";
        }
        if (checkEmptyString(insuredPerson.getName())) {
            errCode = 504;
            errMess = errMess + "|Ошибка распознавания поля 'name'|";
        }
        if (checkEmptyString(insuredPerson.getPatronymic())) {
            errCode = 504;
            errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
        }
        if (checkEmptyDate(insuredPerson.getDateBirth())) {
            errCode = 504;
            errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + insuredPerson.getDateBirth() + "|";
        }
        if (checkEnp(insuredPerson.getEnp())) {
            errCode = 504;
            errMess = errMess + "|Ошибка распознавания поля 'enp' : " + insuredPerson.getEnp() + "|";
        }
        resultRequest.setResCode(errCode);
        resultRequest.setResMess(errMess);

        return resultRequest;
    }

    public boolean checkEmptyDate(Date dateBirth) {
        return dateBirth == null;
    }

    public boolean checkEmptyString(String str) {
        return str.isEmpty();
    }

    public boolean checkEnp(String enp) {
        return enp.length() != 16;
    }

    public boolean checkSpecialtyDoctorCode(int specialtyDoctorCode) {
        if (specialtyDoctorCode != 0){
            return true;
        }
        else return false;
    }

    public boolean checkResultDispensaryAppointment(int resultDispensaryAppointment) {
        if (resultDispensaryAppointment != 0){
            return true;
        } else return false;
    }

    public ResultRequest checkInsuredPatient(IndividualInformingRequestRecord individualInformingRequestRecord) {
        ResultRequest resultRequest = checkInsuredPatientMain(individualInformingRequestRecord);
        int errCode = resultRequest.getResCode();
        String errMess = resultRequest.getResMess();
        if (!checkMKB(individualInformingRequestRecord.getMainDiagnosis())) {
            if (errCode == 500) errMess = "Ошибка распознавания поля 'mainDiagnosis : ";
            else errMess = errMess + "|Ошибка распознавания поля 'mainDiagnosis : ";
            errCode = 504;
            errMess = errMess + individualInformingRequestRecord.getMainDiagnosis();
        }
        if (!individualInformingRequestRecord.getConcomitantDiagnosis().isEmpty()){
            if (!checkMKB(individualInformingRequestRecord.getConcomitantDiagnosis())){
                if (errCode == 500) errMess = "Ошибка распознавания поля 'concomitantDiagnosis' : ";
                else errMess = errMess + "|Ошибка распознавания поля 'concomitantDiagnosis' : ";
                errCode = 504;
                errMess = errMess + individualInformingRequestRecord.getConcomitantDiagnosis();
            }
        }

        resultRequest.setResMess(errMess);
        resultRequest.setResCode(errCode);
        return resultRequest;
    }

    public ResultRequest checkInsuredPatient(IndividualHistoryOnkoCaseRequestRecord individualHistoryOnkoCaseRequestRecord) {
        ResultRequest resultRequest = checkInsuredPatientMain(individualHistoryOnkoCaseRequestRecord);
        int errCode = resultRequest.getResCode();
        String errMess = resultRequest.getResMess();

        if (checkEmptyString(individualHistoryOnkoCaseRequestRecord.getSurname())) {
            errCode = 504;
            errMess = "|Ошибка распознавания поля 'surname'|";
        }
        if (checkEmptyString(individualHistoryOnkoCaseRequestRecord.getName())) {
            errCode = 504;
            errMess = errMess + "|Ошибка распознавания поля 'name'|";
        }
        if (checkEmptyString(individualHistoryOnkoCaseRequestRecord.getPatronymic())) {
            errCode = 504;
            errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
        }
        if (checkEmptyDate(individualHistoryOnkoCaseRequestRecord.getDateBirth())) {
            errCode = 504;
            errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + individualHistoryOnkoCaseRequestRecord.getDateBirth() + "|";
        }
        if (checkEnp(individualHistoryOnkoCaseRequestRecord.getEnp())) {
            errCode = 504;
            errMess = errMess + "|Ошибка распознавания поля 'enp' : " + individualHistoryOnkoCaseRequestRecord.getEnp() + "|";
        }

        resultRequest.setResMess(errMess);
        resultRequest.setResCode(errCode);
        return resultRequest;

    }
}
