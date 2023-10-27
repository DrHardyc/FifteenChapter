package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.ResultRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.*;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo.IndividualHistoryOnkoCaseResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class IndividualHistoryOnkoCaseResponseService implements APIResponseServiceInterface {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private ExamService examService;

    @Autowired
    private IndividualHistoryOnkoCaseRequestRecordService individualHistoryOnkoCaseRequestRecordService;

    @Autowired
    private IndividualHistoryOnkoCaseResponseRecordService individualHistoryOnkoCaseResponseRecordService;

    @Autowired
    private IndividualHistoryOnkoCaseResponseRepo individualHistoryOnkoCaseResponseRepo;

    @Override
    public IndividualHistoryOnkoCaseResponse processing(APIRequest apiRequest,
                                                        APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest = (IndividualHistoryOnkoCaseRequest) apiRequest;
        IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse = (IndividualHistoryOnkoCaseResponse) apiResponse;

        int count = 0;
        List<IndividualHistoryOnkoCaseResponseRecord> individualHistoryOnkoCaseResponseRecords = new ArrayList<>();
        for (IndividualHistoryOnkoCaseRequestRecord individualHistoryOnkoCaseRequestRecord :
                individualHistoryOnkoCaseRequest.getPatients()){

            ResultRequest resultRequest = examService.checkInsuredPatient(individualHistoryOnkoCaseRequestRecord);

            ResultProcessingClass<People> peopleResultProcessingClass = peopleService.search(individualHistoryOnkoCaseRequestRecord);
            if (peopleResultProcessingClass.getProcessingClass() != null) {
                individualHistoryOnkoCaseRequestRecord.setPeople(peopleResultProcessingClass.getProcessingClass());
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
        individualHistoryOnkoCaseResponse.setResultResponseCode(200);
        individualHistoryOnkoCaseResponse.setReqID(individualHistoryOnkoCaseRequest.getReqID());
        individualHistoryOnkoCaseResponse.setPatients(individualHistoryOnkoCaseResponseRecords);
        add(individualHistoryOnkoCaseResponse);

        return individualHistoryOnkoCaseResponse;
    }

    @Override
    public void add(APIResponse apiResponse) {
        apiResponse.setDateBeg(Date.from(Instant.now()));
        apiResponse.setDateEdit(Date.from(Instant.now()));
        individualHistoryOnkoCaseResponseRepo.save((IndividualHistoryOnkoCaseResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        individualHistoryOnkoCaseResponseRepo.saveAll(Collections.singletonList((IndividualHistoryOnkoCaseResponse) apiResponses));
    }

    public IndividualHistoryOnkoCaseResponse getWithReqId(String reqID, int codeMO) {
        return individualHistoryOnkoCaseResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }
}
