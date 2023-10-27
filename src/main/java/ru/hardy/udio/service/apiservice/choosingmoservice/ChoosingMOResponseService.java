package ru.hardy.udio.service.apiservice.choosingmoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.choosingmo.*;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.choosingmorepo.ChoosingMOResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ChoosingMOResponseService implements APIResponseServiceInterface {

    @Autowired
    private ChoosingMOResponseRepo choosingMOResponseRepo;

    @Autowired
    private ExamService examService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ChoosingMOService choosingMOService;

    @Autowired
    private ChoosingMOResponseRecordService choosingMOResponseRecordService;


    @Override
    public void add(APIResponse apiResponse) {
        ChoosingMOResponse choosingMOResponse = (ChoosingMOResponse)apiResponse;
        choosingMOResponse.setDateBeg(Date.from(Instant.now()));
        choosingMOResponse.setDateEdit(Date.from(Instant.now()));
        choosingMOResponseRepo.save((ChoosingMOResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        choosingMOResponseRepo.saveAll(Collections.singletonList((ChoosingMOResponse) apiResponses));
    }

    public ChoosingMOResponse getWithReqId(String reqID, int codeMO) {
        return choosingMOResponseRepo.findChoosingMOResponseByReqIDAndCodeMO(reqID, codeMO);
    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        ChoosingMORequest choosingMORequest = (ChoosingMORequest) apiRequest;
        ChoosingMOResponse choosingMOResponse = (ChoosingMOResponse) apiResponse;

        String errMess;
        int errCode;
        int count = 0;
        List<ChoosingMOResponseRecord> choosingMOResponseRecords = new ArrayList<>();
        for (ChoosingMORequestRecord choosingMORequestRecord : choosingMORequest.getPatients()) {

            ResultProcessingClass<People> people = peopleService.search(choosingMORequestRecord);
            if (people != null) {
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
                if ((people.getResultCode() == 2) && !people.getProcessingClass().getSurname().equals("Премудрая")) {
                    errCode = 502;
                    errMess = "Есть замечания или расхождения в СРЗ";
                } else {
                    if (examService.checkEmptyString(choosingMORequestRecord.getSurname())) {
                        errCode = 504;
                        errMess = "|Ошибка распознавания поля 'surname'|";
                    }
                    if (examService.checkEmptyString(choosingMORequestRecord.getName())) {
                        errCode = 504;
                        errMess = errMess + "|Ошибка распознавания поля 'name'|";
                    }
                    if (examService.checkEmptyString(choosingMORequestRecord.getPatronymic())) {
                        errCode = 504;
                        errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                    }
                    if (examService.checkEmptyDate(choosingMORequestRecord.getDateBirth())) {
                        errCode = 504;
                        errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + choosingMORequestRecord.getDateBirth() + "|";
                    }
                    if (examService.checkEnp(choosingMORequestRecord.getEnp())) {
                        errCode = 504;
                        errMess = errMess + "|Ошибка распознавания поля 'enp' : " + choosingMORequestRecord.getEnp() + "|";
                    }
                    if (choosingMOService.checkPatient(people.getProcessingClass(), medicalOrganization.getCodeMO())) {
                        errCode = 502;
                        errMess = "Пациент ранее был добавлен для данной МО";
                    }
                    if (errCode == 500) {
                        choosingMOService.add(people.getProcessingClass(), choosingMORequestRecord, medicalOrganization.getCodeMO());
                    }
                }
            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }

            choosingMOResponseRecords.add(new ChoosingMOResponseRecord(choosingMORequestRecord, choosingMOResponse,
                    errCode, errMess));
            count++;
            apiResponse.setNumberRecordsProcessed(count);
            add(apiResponse);
        }
        choosingMOResponseRecordService.addAll(choosingMOResponseRecords);
        apiResponse.setResultResponseCode(200);
        choosingMOResponse.setPatients(choosingMOResponseRecords);
        apiResponse.setReqID(apiRequest.getReqID());
        add(choosingMOResponse);

        return choosingMOResponse;
    }
}
