package ru.hardy.udio.service.apiservice.individualinformingservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.ResultRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponseRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualinformingrepo.IndividualInformingResponseRepo;
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
                             IndividualInformingResponse individualInformingResponse) {
        int count = 0;
        List<IndividualInformingResponseRecord> individualInformingResponseRecords = new ArrayList<>();
        for (IndividualInformingRequestRecord individualInformingRequestRecord : individualInformingRequest.getPatients()){

            ResultRequest resultRequest = examService.checkInsuredPatient(individualInformingRequestRecord);
            if (resultRequest.getResCode() == 500) {
                People people = peopleService.search(individualInformingRequestRecord);
                if (people != null) {
                    individualInformingService.add(people, individualInformingRequestRecord);
                } else {
                    resultRequest.setResCode(503);
                    resultRequest.setResMess("Ошибка поиска в СРЗ");
                }
            }

            individualInformingResponseRecords.add(new IndividualInformingResponseRecord(individualInformingRequestRecord,
                    individualInformingResponse, resultRequest.getResCode(), resultRequest.getResMess()));
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
