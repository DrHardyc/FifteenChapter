package ru.hardy.udio.service.apiservice.individualinformingservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.ResultRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponse;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponseRecord;
import ru.hardy.udio.domain.generic.ResultProcessingClass;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualinformingrepo.IndividualInformingResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class IndividualInformingResponseService implements APIResponseServiceInterface {

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

    @Override
    public void add(APIResponse apiResponse){
        apiResponse.setDateBeg(Date.from(Instant.now()));
        apiResponse.setDateEdit(Date.from(Instant.now()));
        individualInformingResponseRepo.save((IndividualInformingResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        individualInformingResponseRepo.saveAll(Collections.singletonList((IndividualInformingResponse) apiResponses));
    }

    public IndividualInformingResponse getWithReqId(String reqID, int codeMO) {
        return individualInformingResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    @Override
    public IndividualInformingResponse processing(APIRequest apiRequest,
                                                  APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        IndividualInformingRequest individualInformingRequest = (IndividualInformingRequest) apiRequest;
        IndividualInformingResponse individualInformingResponse = (IndividualInformingResponse) apiResponse;

        int count = 0;
        List<IndividualInformingResponseRecord> individualInformingResponseRecords = new ArrayList<>();
        for (IndividualInformingRequestRecord individualInformingRequestRecord : individualInformingRequest.getPatients()){

            ResultRequest resultRequest = examService.checkInsuredPatient(individualInformingRequestRecord);
            if (resultRequest.getResCode() == 500) {
                ResultProcessingClass<People> peopleResultProcessingClass = peopleService.search(individualInformingRequestRecord);
                if (peopleResultProcessingClass.getProcessingClass() != null) {
                    if (peopleResultProcessingClass.getResultCode() == 2){
                        resultRequest.setResCode(502);
                        resultRequest.setResMess("Есть замечания или расхождения в СРЗ");
                    }
                    individualInformingService.add(peopleResultProcessingClass.getProcessingClass(), individualInformingRequestRecord);
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
        individualInformingResponse.setResultResponseCode(200);
        individualInformingResponse.setPatients(individualInformingResponseRecords);
        individualInformingResponse.setReqID(individualInformingRequest.getReqID());
        add(individualInformingResponse);

        return individualInformingResponse;
    }
}
