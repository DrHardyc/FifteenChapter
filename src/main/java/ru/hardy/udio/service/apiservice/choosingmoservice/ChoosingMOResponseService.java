package ru.hardy.udio.service.apiservice.choosingmoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequest;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponseRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.choosingmorepo.ChoosingMOResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.TokenService;
import ru.hardy.udio.service.apiservice.ExamService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoosingMOResponseService {

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

    public void add(ChoosingMOResponse choosingMOResponse){
        choosingMOResponseRepo.save(choosingMOResponse);
    }

    public ChoosingMOResponse getWithReqId(String reqID, int codeMO) {
        return choosingMOResponseRepo.findChoosingMOResponseByReqIDAndCodeMO(reqID, codeMO);
    }

    public ChoosingMOResponse processing(ChoosingMORequest choosingMORequest,
                                         ChoosingMOResponse choosingMOResponse,
                                         int codeMO) {

        String errMess;
        int errCode;
        int count = 0;
        List<ChoosingMOResponseRecord> choosingMOResponseRecords = new ArrayList<>();
        for (ChoosingMORequestRecord choosingMORequestRecord : choosingMORequest.getPatients()){

            People people = peopleService.search(choosingMORequestRecord);
            if (people != null) {
                errCode = 500;
                errMess = "Успешное выполнение обработки записи";
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
                if (choosingMOService.checkPatient(people, codeMO)){
                    errCode = 502;
                    errMess = "Пациент ранее был добавлен для данной МО";
                }
                if (errCode == 500){
                    choosingMOService.add(people, choosingMORequestRecord, codeMO);
                }
            } else {
                errCode = 503;
                errMess = "Ошибка поиска в СРЗ";
            }

            choosingMOResponseRecords.add(new ChoosingMOResponseRecord(choosingMORequestRecord, choosingMOResponse,
                    errCode, errMess));
            count++;
            choosingMOResponse.setNumberRecordsProcessed(count);
            add(choosingMOResponse);
        }
        choosingMOResponseRecordService.addAll(choosingMOResponseRecords);
        choosingMOResponse.setResultRequestCode(200);
        choosingMOResponse.setPatients(choosingMOResponseRecords);
        choosingMOResponse.setReqID(choosingMORequest.getReqID());
        add(choosingMOResponse);

        return choosingMOResponse;
    }
}
