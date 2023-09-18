package ru.hardy.udio.service.apiservice.dodatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.dodatapatients.*;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.dodatapatientsrepo.DoDataPatientsResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoDataPatientsResponseService {

    @Autowired
    private DoDataPatientsResponseRepo doDataPatientsResponseRepo;

    @Autowired
    private ExamService examService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private DODataPatientsResponseRecordService doDataPatientsResponseRecordService;

    @Autowired
    private DODataPatientsService doDataPatientsService;

    public void add(DODataPatientsResponse doDataPatientsResponse){
        doDataPatientsResponseRepo.save(doDataPatientsResponse);
    }

    public DODataPatientsResponse getWithReqId(String reqID, int codeMO) {
        return doDataPatientsResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    public DODataPatientsResponse processing(DODataPatientsRequest doDataPatientsRequest,
                                             DODataPatientsResponse doDataPatientsResponse, int codeMO) {
        String errMess;
        int errCode;
        int count = 0;
        List<DODataPatientsResponseRecord> doDataPatientsResponseRecords = new ArrayList<>();
        for (DODataPatientsRequestRecord doDataPatientsRequestRecord : doDataPatientsRequest.getPatients()){

            People people = peopleService.search(doDataPatientsRequestRecord);
            if (people != null) {
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
                if (examService.checkEmptyString(doDataPatientsRequestRecord.getSurname())) {
                    errCode = 504;
                    errMess = "|Ошибка распознавания поля 'surname'|";
                }
                if (examService.checkEmptyString(doDataPatientsRequestRecord.getName())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'name'|";
                }
                if (examService.checkEmptyString(doDataPatientsRequestRecord.getPatronymic())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                }
                if (!examService.checkEmptyDate(doDataPatientsRequestRecord.getDateBirth())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + doDataPatientsRequestRecord.getDateBirth() + "|";
                }
                if (!examService.checkEnp(doDataPatientsRequestRecord.getEnp())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'enp' : " + doDataPatientsRequestRecord.getEnp() + "|";
                }
                if (doDataPatientsService.checkPatient(people, codeMO)){
                    errCode = 502;
                    errMess = "Пациент ранее был добавлен для данной МО";
                }
                if (errCode == 500){
                    doDataPatientsService.add(people, doDataPatientsRequest, codeMO);
                }
            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }

            doDataPatientsResponseRecords.add(new DODataPatientsResponseRecord(doDataPatientsRequestRecord, doDataPatientsResponse,
                    errCode, errMess));
            count++;
            doDataPatientsResponse.setNumberRecordsProcessed(count);
            add(doDataPatientsResponse);
        }
        doDataPatientsResponseRecordService.addAll(doDataPatientsResponseRecords);
        doDataPatientsResponse.setResultRequestCode(200);
        doDataPatientsResponse.setPatients(doDataPatientsResponseRecords);
        doDataPatientsResponse.setReqID(doDataPatientsRequest.getReqID());
        add(doDataPatientsResponse);

        return doDataPatientsResponse;
    }
}
