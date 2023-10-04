package ru.hardy.udio.service.apiservice.individualinformingservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponseRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualinforming.IndividualInformingResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndividualInformingResponseService {

    @Autowired
    private IndividualInformingResponseRepo individualInformingResponseRepo;

    @Autowired
    private ExamService examService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private IndividualInformingService individualInformingService;

    @Autowired
    private IndividualInformingResponseRecordService individualInformingResponseRecordService;

    public void add(IndividualInformingResponse individualInformingResponse){
        individualInformingResponseRepo.save(individualInformingResponse);
    }

    public IndividualInformingResponse getWithReqId(String reqID, int codeMO) {
        return individualInformingResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    public IndividualInformingResponse processing(IndividualInformingRequest individualInformingRequest,
                             IndividualInformingResponse individualInformingResponse, int codeMO) {
        String errMess;
        int errCode;
        int count = 0;
        List<IndividualInformingResponseRecord> individualInformingResponseRecords = new ArrayList<>();
        for (IndividualInformingRequestRecord individualInformingRequestRecord : individualInformingRequest.getPatients()){

            People people = peopleService.search(individualInformingRequestRecord);
            if (people != null) {
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
                if (examService.checkEmptyString(individualInformingRequestRecord.getSurname())) {
                    errCode = 504;
                    errMess = "|Ошибка распознавания поля 'surname'|";
                }
                if (examService.checkEmptyString(individualInformingRequestRecord.getName())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'name'|";
                }
                if (examService.checkEmptyString(individualInformingRequestRecord.getPatronymic())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'Patronymic'|";
                }
                if (examService.checkEmptyDate(individualInformingRequestRecord.getDateBirth())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'dateBirth' : " + individualInformingRequestRecord.getDateBirth() + "|";
                }
                if (examService.checkEnp(individualInformingRequestRecord.getEnp())) {
                    errCode = 504;
                    errMess = errMess + "|Ошибка распознавания поля 'enp' : " + individualInformingRequestRecord.getEnp() + "|";
                }
//                if (individualInformingService.checkPatient(people, codeMO, individualInformingRequestRecord.getMainDiagnosis())){
//                    errCode = 502;
//                    errMess = "Пациент c таким диагнозом ранее был добавлен для данной МО";
//                }
                if (errCode == 500){
                    individualInformingService.add(people, individualInformingRequestRecord, codeMO);
                }
            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }

            individualInformingResponseRecords.add(new IndividualInformingResponseRecord(individualInformingRequestRecord,
                    individualInformingResponse, errCode, errMess));
            count++;
            individualInformingResponse.setNumberRecordsProcessed(count);
            add(individualInformingResponse);
        }
        individualInformingResponseRecordService.addAll(individualInformingResponseRecords);
        individualInformingResponse.setResultRequestCode(200);
        individualInformingResponse.setPatients(individualInformingResponseRecords);
        individualInformingResponse.setReqID(individualInformingRequest.getReqID());
        add(individualInformingResponse);

        return individualInformingResponse;
    }
}
