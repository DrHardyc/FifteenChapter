package ru.hardy.udio.service.apiservice.recommendationspatientservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponseRecord;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationResponse;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientRequest;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientRequestRecord;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientResponse;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientResponseRecord;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.recommendationspatientrepo.RecommendationsPatientResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RecommendationsPatientResponseService implements APIResponseServiceInterface {

    @Autowired
    private RecommendationsPatientResponseRepo recommendationsPatientResponseRepo;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ExamService examService;

    @Autowired
    private RecommendationsPatientService recommendationsPatientService;

    @Autowired
    public RecommendationsPatientResponseRecordService recommendationsPatientResponseRecordService;


    @Override
    public void add(APIResponse apiResponse) {
        recommendationsPatientResponseRepo.save((RecommendationsPatientResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        recommendationsPatientResponseRepo.saveAll(Collections.singletonList((RecommendationsPatientResponse) apiResponses));
    }

    @Override
    public APIResponse processing(APIRequest apiRequest, APIResponse apiResponse, int codeMO) {
        RecommendationsPatientRequest recommendationsPatientRequest = (RecommendationsPatientRequest) apiRequest;
        RecommendationsPatientResponse recommendationsPatientResponse = (RecommendationsPatientResponse) apiResponse;

        String errMess;
        int errCode;
        int count = 0;
        List<RecommendationsPatientResponseRecord> recommendationsPatientResponseRecords = new ArrayList<>();
        for (RecommendationsPatientRequestRecord recommendationsPatientRequestRecord : recommendationsPatientRequest.getPatients()) {

            ResultProcessingClass<People> people = peopleService.search(recommendationsPatientRequestRecord);
            if (people != null) {
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
                if ((people.getResultCode() == 2) && !people.getProcessingClass().getSurname().equals("Премудрая")) {
                    errCode = 502;
                    errMess = "Есть замечания или расхождения в СРЗ";
                } else {
                    if (examService.checkEmptyString(recommendationsPatientRequestRecord.getSurname())) {
                        errCode = 504;
                        errMess = "|Ошибка распознавания поля 'surname'|";
                    }
                    if (examService.checkEmptyString(recommendationsPatientRequestRecord.getName())) {
                        errCode = 504;
                        errMess = errMess + "|Ошибка распознавания поля 'name'|";
                    }
                    if (examService.checkEmptyString(recommendationsPatientRequestRecord.getPatronymic())) {
                        errCode = 504;
                        errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                    }
                    if (examService.checkEmptyDate(recommendationsPatientRequestRecord.getDateBirth())) {
                        errCode = 504;
                        errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + recommendationsPatientRequestRecord.getDateBirth() + "|";
                    }
                    if (examService.checkEnp(recommendationsPatientRequestRecord.getEnp())) {
                        errCode = 504;
                        errMess = errMess + "|Ошибка распознавания поля 'enp' : " + recommendationsPatientRequestRecord.getEnp() + "|";
                    }
                    if (recommendationsPatientService.checkPatient(people.getProcessingClass(),
                            recommendationsPatientRequestRecord.getRecommendation())) {
                        errCode = 502;
                        errMess = "Пациент ранее был добавлен для данной МО";
                    }
                    if (errCode == 500) {
                        recommendationsPatientService.add(people.getProcessingClass(), recommendationsPatientRequestRecord);
                    }
                }
            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }

            recommendationsPatientResponseRecords.add(new RecommendationsPatientResponseRecord(
                    recommendationsPatientRequestRecord, recommendationsPatientResponse,
                    errCode, errMess));
            count++;
            apiResponse.setNumberRecordsProcessed(count);
            add(recommendationsPatientResponse);
        }
        recommendationsPatientResponseRecordService.addAll(recommendationsPatientResponseRecords);
        recommendationsPatientResponse.setResultResponseCode(200);
        recommendationsPatientResponse.setPatients(recommendationsPatientResponseRecords);
        recommendationsPatientResponse.setReqID(apiRequest.getReqID());
        add(recommendationsPatientResponse);

        return recommendationsPatientResponse;
    }

    public APIResponse getWithReqId(String reqID, int codeMO) {
        return recommendationsPatientResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }
}
