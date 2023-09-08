package ru.hardy.udio.service.apiservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequest;
import ru.hardy.udio.domain.api.PatientOnkoCaseRequestRecord;
import ru.hardy.udio.domain.api.PatientOnkoCaseResponse;
import ru.hardy.udio.domain.api.PatientOnkoCaseResponseRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.PatientOnkoCaseResponseRepo;
import ru.hardy.udio.service.PeopleService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientOnkoCaseResponseService {

    @Autowired
    private PatientOnkoCaseResponseRepo patientOnkoCaseResponseRepo;

    @Autowired
    private PatientOnkoCaseResponseRecordService patientOnkoCaseResponseRecordService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ExamService examService;

    @Autowired
    private PatientOnkoCaseService patientOnkoCaseService;

    @Autowired
    private PatientOnkoCaseResponseService patientOnkoCaseResponseService;

    public void add(PatientOnkoCaseResponse patientOnkoCaseResponse){
        patientOnkoCaseResponseRepo.save(patientOnkoCaseResponse);
    }

    public PatientOnkoCaseResponse processing(PatientOnkoCaseRequest patientOnkoCaseRequest,
                                              PatientOnkoCaseResponse patientOnkoCaseResponse, String token) {

        String errMess;
        int errCode;
        int count = 0;
        List<PatientOnkoCaseResponseRecord> patientOnkoCaseResponseRecords = new ArrayList<>();
        for (PatientOnkoCaseRequestRecord patientOnkoCaseRequestRecord : patientOnkoCaseRequest.getPatients()){
            People people = peopleService.search(patientOnkoCaseRequestRecord);
            if (people != null){
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
                if (examService.checkEmptyString(patientOnkoCaseRequestRecord.getSurname())) {
                    errCode = 504;
                    errMess = "|Ошибка распознавания поля 'surname'|";
                }
                if (examService.checkEmptyString(patientOnkoCaseRequestRecord.getName())){
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'name'|";
                }
                if (examService.checkEmptyString(patientOnkoCaseRequestRecord.getPatronymic())){
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                }
                if (!examService.checkEmptyDate(patientOnkoCaseRequestRecord.getDateBirth())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + patientOnkoCaseRequestRecord.getDateBirth() + "|";
                }
                if (!examService.checkEnp(patientOnkoCaseRequestRecord.getEnp())){
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'enp' : " + patientOnkoCaseRequestRecord.getEnp() + "|";
                }
                if (examService.checkEmptyInt(patientOnkoCaseRequestRecord.getFirstIdentified())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'firstIdentified' : " + patientOnkoCaseRequestRecord.getFirstIdentified() + "|";
                }
                if (!examService.checkSex(patientOnkoCaseRequestRecord.getSex())){
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'sex' : " + patientOnkoCaseRequestRecord.getSex() + "|";
                }
                if (!examService.checkMKB(patientOnkoCaseRequestRecord.getMainDiagnosis())){
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'mainDiagnosis' : " + patientOnkoCaseRequestRecord.getMainDiagnosis() + "|";
                } else {
                    patientOnkoCaseService.add(patientOnkoCaseRequestRecord, people, token);
                }
            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }
            patientOnkoCaseResponseRecords.add(new PatientOnkoCaseResponseRecord(patientOnkoCaseRequestRecord,
                    patientOnkoCaseResponse, errCode, errMess));
            patientOnkoCaseResponse.setResultRequestCode(count++);
            patientOnkoCaseResponseService.add(patientOnkoCaseResponse);
        }
        patientOnkoCaseResponse.setPatients(patientOnkoCaseResponseRecords);
        patientOnkoCaseResponseRecordService.addAll(patientOnkoCaseResponseRecords);

        return patientOnkoCaseResponse;
    }
}
