package ru.hardy.udio.service.apiservice.individualhistoryinformingresponseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequestRecord;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo.IndividualHistoryInformingResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;
import ru.hardy.udio.service.apiservice.individualinformingservice.IndividualInformingRequestRecordService;
import ru.hardy.udio.service.apiservice.padatapatientsservice.PADataPatientRequestRecordService;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndividualHistoryInformingResponseService {

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
    private IndividualHistoryInformingService individualHistoryInformingService;


    public void add(IndividualHistoryInformingResponse individualHistoryInformingResponse) {
        individualHistoryInformingResponseRepo.save(individualHistoryInformingResponse);
    }

    public IndividualHistoryInformingResponse getWithReqId(String reqID, int codeMO) {
        return individualHistoryInformingResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    public IndividualHistoryInformingResponse processing(IndividualHistoryInformingRequest individualHistoryInformingRequest,
                             IndividualHistoryInformingResponse individualHistoryInformingResponse, int codeMO) {
        String errMess;
        int errCode;
        int count = 0;
        List<IndividualHistoryInforming> individualHistoryInformings = new ArrayList<>();

        for (IndividualHistoryInformingRequestRecord individualHistoryInformingRequestRecord :
                individualHistoryInformingRequest.getPatients()){

            People people = peopleService.search(individualHistoryInformingRequestRecord);
            List<IndividualInformingRequestRecord> individualInformingRequestRecords = null;
            List<PADataPatientRequestRecord> paDataPatientRequestRecords = null;
            if (people != null) {
                individualInformingRequestRecords = individualInformingRequestRecordService.getAllByPeople(people);
                paDataPatientRequestRecords = paDataPatientRequestRecordService.getAllByPeople(people);
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
                errMess = "Ошибка поиска в СРЗ";
            }

//            individualHistoryInformingsRe.add(new IndividualHistoryInforming(people,
//                    individualInformingRequestRecords,
//                    paDataPatientRequestRecords));
            count++;
            individualHistoryInformingResponse.setNumberRecordsProcessed(count);
            add(individualHistoryInformingResponse);
        }
        individualHistoryInformingService.addAll(individualHistoryInformings);
        individualHistoryInformingResponse.setResultRequestCode(200);
        //individualHistoryInformingResponse.setPatients(individualHistoryInformings);
        individualHistoryInformingResponse.setReqID(individualHistoryInformingRequest.getReqID());
        add(individualHistoryInformingResponse);

        return individualHistoryInformingResponse;
    }
}
