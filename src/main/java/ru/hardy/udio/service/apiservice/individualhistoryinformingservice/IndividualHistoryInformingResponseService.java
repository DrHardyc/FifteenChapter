package ru.hardy.udio.service.apiservice.individualhistoryinformingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.individualhistoryinforming.*;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo.IndividualHistoryInformingResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestRecordService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.mo.PADataPatientRequestRecordService;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class IndividualHistoryInformingResponseService implements APIResponseServiceInterface {

    @Autowired
    private IndividualHistoryInformingResponseRepo individualHistoryInformingResponseRepo;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ExamService examService;

    @Autowired
    private IndividualInformingRequestRecordService individualInformingRequestRecordService;

    @Autowired
    private PADataPatientRequestRecordService paDataPatientRequestRecordService;

    @Autowired
    private IndividualHistoryInformingResponseRecordService individualHistoryInformingResponseRecordService;

    @Autowired
    private IndividualHistoryInformingRequestRecordService individualHistoryInformingRequestRecordService;


    @Override
    public void add(APIResponse apiResponse) {
        apiResponse.setDateBeg(Date.from(Instant.now()));
        apiResponse.setDateEdit(Date.from(Instant.now()));
        individualHistoryInformingResponseRepo.save((IndividualHistoryInformingResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        individualHistoryInformingResponseRepo.saveAll(Collections.singletonList((IndividualHistoryInformingResponse) apiResponses));
    }

    public IndividualHistoryInformingResponse getWithReqId(String reqID, int codeMO) {
        return individualHistoryInformingResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    @Override
    public IndividualHistoryInformingResponse processing(APIRequest apiRequest,
                                                         APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        IndividualHistoryInformingRequest individualHistoryInformingRequest = (IndividualHistoryInformingRequest) apiRequest;
        IndividualHistoryInformingResponse individualHistoryInformingResponse = (IndividualHistoryInformingResponse) apiResponse;

        String errMess;
        int errCode;
        int count = 0;
        List<IndividualHistoryInformingResponseRecord> individualHistoryInformingResponseRecords = new ArrayList<>();
        List<ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord> individualInformingRequestRecords = null;
        List<PADataPatientRequestRecord> paDataPatientRequestRecords = null;
        for (IndividualHistoryInformingRequestRecord individualHistoryInformingRequestRecord :
                individualHistoryInformingRequest.getPatients()){

            ResultProcessingClass<People> peopleResultProcessingClass = peopleService.search(individualHistoryInformingRequestRecord);

            if (peopleResultProcessingClass != null) {
                individualInformingRequestRecords = individualInformingRequestRecordService.getAllByPeople(peopleResultProcessingClass.getProcessingClass());
                paDataPatientRequestRecords = paDataPatientRequestRecordService.getAllByPeople(peopleResultProcessingClass.getProcessingClass());
                individualHistoryInformingRequestRecord.setPeople(peopleResultProcessingClass.getProcessingClass());
                individualHistoryInformingRequestRecordService.add(individualHistoryInformingRequestRecord);
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
                if (examService.checkEmptyString(individualHistoryInformingRequestRecord.getSurname())) {
                    errCode = 504;
                    errMess = "|Ошибка распознавания поля 'surname'|";
                }
                if (examService.checkEmptyString(individualHistoryInformingRequestRecord.getName())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'name'|";
                }
                if (examService.checkEmptyString(individualHistoryInformingRequestRecord.getPatronymic())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                }
                if (examService.checkEmptyDate(individualHistoryInformingRequestRecord.getDateBirth())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + individualHistoryInformingRequestRecord.getDateBirth() + "|";
                }
                if (examService.checkEnp(individualHistoryInformingRequestRecord.getEnp())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'enp' : " + individualHistoryInformingRequestRecord.getEnp() + "|";
                }
            } else {
                errCode = 503;
                errMess = "Пациент не найден";
            }
            if (errCode == 500) {
                individualHistoryInformingResponseRecords.add(new IndividualHistoryInformingResponseRecord(
                        individualHistoryInformingRequestRecord, individualHistoryInformingResponse,
                        individualInformingRequestRecords, paDataPatientRequestRecords,
                        errCode, errMess));
            } else {
                individualHistoryInformingResponseRecords.add(new IndividualHistoryInformingResponseRecord(
                        individualHistoryInformingRequestRecord, individualHistoryInformingResponse,
                        null, null,
                        errCode, errMess));
            }

            count++;
            individualHistoryInformingResponse.setNumberRecordsProcessed(count);
            add(individualHistoryInformingResponse);
        }
        individualHistoryInformingResponseRecordService.addAll(individualHistoryInformingResponseRecords);
        individualHistoryInformingResponse.setResultResponseCode(200);
        individualHistoryInformingResponse.setReqID(individualHistoryInformingRequest.getReqID());
        individualHistoryInformingResponse.setPatients(individualHistoryInformingResponseRecords);
        add(individualHistoryInformingResponse);

        return individualHistoryInformingResponse;
    }


}
