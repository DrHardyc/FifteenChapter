package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.api.ResultRequest;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponse;
import ru.hardy.udio.domain.api.individualhistoryonkocase.*;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo.IndividualHistoryOnkoCaseResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndividualHistoryOnkoCaseResponseService {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ExamService examService;

    @Autowired
    private IndividualHistoryOnkoCaseRequestRecordService individualHistoryOnkoCaseRequestRecordService;

    @Autowired
    private IndividualHistoryOnkoCaseResponseRecordService individualHistoryOnkoCaseResponseRecordService;

    public IndividualHistoryOnkoCaseResponse processing(IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest,
                                                        IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse){
        int count = 0;
        List<IndividualHistoryOnkoCaseResponseRecord> individualHistoryOnkoCaseResponseRecords = new ArrayList<>();
        for (IndividualHistoryOnkoCaseRequestRecord individualHistoryOnkoCaseRequestRecord :
                individualHistoryOnkoCaseRequest.getPatients()){

            ResultRequest resultRequest = examService.checkInsuredPatient(individualHistoryOnkoCaseRequestRecord);

            People people = peopleService.search(individualHistoryOnkoCaseRequestRecord);
            if (people != null) {
                individualHistoryOnkoCaseRequestRecord.setPeople(people);
                individualHistoryOnkoCaseRequestRecordService.add(individualHistoryOnkoCaseRequestRecord);
            } else {
                resultRequest.setResCode(503);
                resultRequest.setResMess("Ошибка поиска в СРЗ");
            }
            List<InsuranceCase> insuranceCases = individualHistoryOnkoCaseResponseRecordService.getInsuredCases(individualHistoryOnkoCaseRequestRecord);
            individualHistoryOnkoCaseResponseRecords.add(new IndividualHistoryOnkoCaseResponseRecord(
                    individualHistoryOnkoCaseRequestRecord, individualHistoryOnkoCaseResponse, insuranceCases,
                    resultRequest.getResCode(), resultRequest.getResMess()));

            count++;
            individualHistoryOnkoCaseResponse.setNumberRecordsProcessed(count);
            add(individualHistoryOnkoCaseResponse);
        }
        individualHistoryOnkoCaseResponseRecordService.addAll(individualHistoryOnkoCaseResponseRecords);
        individualHistoryOnkoCaseResponse.setResultRequestCode(200);
        individualHistoryOnkoCaseResponse.setReqID(individualHistoryOnkoCaseRequest.getReqID());
        individualHistoryOnkoCaseResponse.setPatients(individualHistoryOnkoCaseResponseRecords);
        add(individualHistoryOnkoCaseResponse);



        return individualHistoryOnkoCaseResponse;
    }

    public void add(IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse) {
        individualHistoryOnkoCaseResponseRepo.save(individualHistoryOnkoCaseResponse);
    }

    @Autowired
    private IndividualHistoryOnkoCaseResponseRepo individualHistoryOnkoCaseResponseRepo;

    public IndividualHistoryOnkoCaseResponse getWithReqId(String reqID, int codeMO) {
        return individualHistoryOnkoCaseResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }
}
