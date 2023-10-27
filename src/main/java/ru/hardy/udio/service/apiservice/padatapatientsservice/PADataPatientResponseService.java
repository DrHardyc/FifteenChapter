package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.ResultRequest;
import ru.hardy.udio.domain.api.padatapatients.*;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientResponseRepo;
import ru.hardy.udio.service.apiservice.ExamService;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PADataPatientResponseService implements APIResponseServiceInterface {

    @Autowired
    private PADataPatientResponseRepo paDataPatientResponseRepo;

    @Autowired
    private ExamService examService;

    @Autowired
    private PADataPatientResponseRecordService paDataPatientResponseRecordService;

    @Override
    public void add(APIResponse apiResponse){
        apiResponse.setDateBeg(Date.from(Instant.now()));
        apiResponse.setDateEdit(Date.from(Instant.now()));
        paDataPatientResponseRepo.save((PADataPatientResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        paDataPatientResponseRepo.saveAll(Collections.singletonList((PADataPatientResponse) apiResponses));
    }

    public PADataPatientResponse getWithReqId(String reqID, int codeMO) {
        return paDataPatientResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    @Override
    public PADataPatientResponse processing(APIRequest apiRequest,
                                            APIResponse apiResponse, int codeMO) {
        PADataPatientRequest paDataPatientRequest = (PADataPatientRequest) apiRequest;
        PADataPatientResponse paDataPatientResponse = (PADataPatientResponse) apiResponse;
        int count = 0;
        List<PADataPatientResponseRecord> paDataPatientResponseRecords = new ArrayList<>();
        for (PADataPatientRequestRecord paDataPatientRequestRecord : paDataPatientRequest.getPatients()){
            ResultRequest resultRequest = examService.checkInsuredPatient(paDataPatientRequestRecord);

            paDataPatientResponseRecords.add(new PADataPatientResponseRecord(paDataPatientRequestRecord, paDataPatientResponse,
                    resultRequest.getResCode(), resultRequest.getResMess()));
            count++;
            paDataPatientResponse.setNumberRecordsProcessed(count);
            add(paDataPatientResponse);
        }
        paDataPatientResponseRecordService.addAll(paDataPatientResponseRecords);
        paDataPatientResponse.setResultResponseCode(200);
        paDataPatientResponse.setPatients(paDataPatientResponseRecords);
        paDataPatientResponse.setReqID(paDataPatientRequest.getReqID());
        add(paDataPatientResponse);

        return paDataPatientResponse;
    }
}
