package ru.hardy.udio.service.apiservice.hospitalizationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.hospitalization.*;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.hospitalizationrepo.HospitalizationResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HospitalizationResponseService implements APIResponseServiceInterface {
    @Autowired
    private PeopleService peopleService;
    @Autowired
    private ExamService examService;

    @Autowired
    private HospitalizationService hospitalizationService;

    @Autowired
    private HospitalizationResponseRepo hospitalizationResponseRepo;

    @Autowired
    private HospitalizationResponseRecordService hospitalizationResponseRecordService;

    @Override
    public void add(APIResponse apiResponse) {
        hospitalizationResponseRepo.save((HospitalizationResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        hospitalizationResponseRepo.saveAll(Collections.singletonList((HospitalizationResponse) apiResponses));
    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, int codeMO) {
        HospitalizationRequest hospitalizationRequest = (HospitalizationRequest) apiRequest;
        HospitalizationResponse hospitalizationResponse = (HospitalizationResponse) apiResponse;

        String errMess;
        int errCode;
        int count = 0;
        List<HospitalizationResponseRecord> hospitalizationResponseRecords = new ArrayList<>();
        for (HospitalizationRequestRecord hospitalizationRequestRecord: hospitalizationRequest.getDepartments()) {
            List<HospitalizationResponseRecordPatient> hospitalizationResponseRecordPatients = new ArrayList<>();
            for (HospitalizationRequestRecordPatient hospitalizationRequestRecordPatient : hospitalizationRequestRecord.getPatients()) {
                ResultProcessingClass<People> people = peopleService.search(hospitalizationRequestRecordPatient);
                if (people != null) {
                    errCode = 500;
                    errMess = "Успешное выполнение обработки записи";
                    if ((people.getResultCode() == 2) && !people.getProcessingClass().getSurname().equals("Премудрая")) {
                        errCode = 502;
                        errMess = "Есть замечания или расхождения в СРЗ";
                    } else {
                        if (examService.checkEmptyString(hospitalizationRequestRecordPatient.getSurname())) {
                            errCode = 504;
                            errMess = "|Ошибка распознавания поля 'surname'|";
                        }
                        if (examService.checkEmptyString(hospitalizationRequestRecordPatient.getName())) {
                            errCode = 504;
                            errMess = errMess + "|Ошибка распознавания поля 'name'|";
                        }
                        if (examService.checkEmptyString(hospitalizationRequestRecordPatient.getPatronymic())) {
                            errCode = 504;
                            errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                        }
                        if (examService.checkEmptyDate(hospitalizationRequestRecordPatient.getDateBirth())) {
                            errCode = 504;
                            errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + hospitalizationRequestRecordPatient.getDateBirth() + "|";
                        }
                        if (examService.checkEnp(hospitalizationRequestRecordPatient.getEnp())) {
                            errCode = 504;
                            errMess = errMess + "|Ошибка распознавания поля 'enp' : " + hospitalizationRequestRecordPatient.getEnp() + "|";
                        }
                        if (hospitalizationService.checkPatient(people.getProcessingClass(),
                                hospitalizationRequestRecordPatient.getMainDiagnosis(),
                                hospitalizationRequestRecordPatient.getHospitalization())) {
                            errCode = 502;
                            errMess = "Пациент ранее был добавлен для данной МО";
                        }
                        if (errCode == 500) {
                            hospitalizationService.add(people.getProcessingClass(), hospitalizationRequestRecordPatient);
                        }
                    }
                } else {
                    errCode = 503;
                    errMess = "Ошибка поиска в СРЗ";
                }

                hospitalizationResponseRecordPatients.add(new HospitalizationResponseRecordPatient(
                        hospitalizationRequestRecordPatient, errCode, errMess));
                count++;
                hospitalizationResponse.setNumberRecordsProcessed(count);
                add(hospitalizationResponse);
            }
            hospitalizationResponseRecords.add(new HospitalizationResponseRecord(
                    hospitalizationRequestRecord, hospitalizationResponse, hospitalizationResponseRecordPatients));
        }
        hospitalizationResponseRecordService.addAll(hospitalizationResponseRecords);
        hospitalizationResponse.setResultResponseCode(200);
        hospitalizationResponse.setDepartments(hospitalizationResponseRecords);
        hospitalizationResponse.setReqID(apiRequest.getReqID());
        add(hospitalizationResponse);
        return hospitalizationResponse;
    }

    public HospitalizationResponse getWithReqId(String reqID, int codeMO) {
        return hospitalizationResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    public boolean checkChild(APIRequest apiRequest) {
        return ((HospitalizationRequest) apiRequest).getDepartments() != null;
    }
}
