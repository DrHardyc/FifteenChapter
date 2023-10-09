package ru.hardy.udio.service.apiservice.padatapatientsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.ResultRequest;
import ru.hardy.udio.domain.api.padatapatients.*;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.padatapatientsrepo.PADataPatientResponseRepo;
import ru.hardy.udio.service.PeopleService;
import ru.hardy.udio.service.apiservice.ExamService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PADataPatientResponseService {

    @Autowired
    private PADataPatientResponseRepo paDataPatientResponseRepo;

    @Autowired
    private ExamService examService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private PADataPatientResponseRecordService paDataPatientResponseRecordService;

    @Autowired
    private PADataPatientService paDataPatientsService;

    public void add(PADataPatientResponse paDataPatientResponse){
        paDataPatientResponseRepo.save(paDataPatientResponse);
    }

    public PADataPatientResponse getWithReqId(String reqID, int codeMO) {
        return paDataPatientResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    public PADataPatientResponse processing(PADataPatientRequest paDataPatientRequest,
                                            PADataPatientResponse paDataPatientResponse) {
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
        paDataPatientResponse.setResultRequestCode(200);
        paDataPatientResponse.setPatients(paDataPatientResponseRecords);
        paDataPatientResponse.setReqID(paDataPatientRequest.getReqID());
        add(paDataPatientResponse);

        return paDataPatientResponse;
    }
}
