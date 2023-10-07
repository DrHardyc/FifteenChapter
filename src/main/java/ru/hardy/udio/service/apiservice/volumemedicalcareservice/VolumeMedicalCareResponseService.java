package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequest;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareRequestRecord;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponse;
import ru.hardy.udio.domain.api.volumemedicalcare.VolumeMedicalCareResponseRecord;
import ru.hardy.udio.repo.apirepo.volumemedicalmarerepo.VolumeMedicalCareResponseRecordService;
import ru.hardy.udio.repo.apirepo.volumemedicalmarerepo.VolumeMedicalCareResponseRepo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VolumeMedicalCareResponseService {

    @Autowired
    private VolumeMedicalCareResponseRepo volumeMedicalCareRequestRepo;

    @Autowired
    private VolumeMedicalCareService volumeMedicalCareService;

    @Autowired
    private VolumeMedicalCareResponseRecordService volumeMedicalCareResponseRecordService;


    public VolumeMedicalCareResponse getWithReqId(String reqID, int codeMO) {
        return volumeMedicalCareRequestRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    public void add(VolumeMedicalCareResponse volumeMedicalCareResponse) {
        volumeMedicalCareRequestRepo.save(volumeMedicalCareResponse);
    }

    public VolumeMedicalCareResponse processing(VolumeMedicalCareRequest volumeMedicalCareRequest,
                             VolumeMedicalCareResponse volumeMedicalCareResponse,
                             int codeMO) {
        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<VolumeMedicalCareResponseRecord> volumeMedicalCareResponseRecords = new ArrayList<>();

        for (VolumeMedicalCareRequestRecord departmentRequest : volumeMedicalCareRequest.getDepartments()){
            volumeMedicalCareService.add(departmentRequest, codeMO);
            volumeMedicalCareResponse.setNumberRecordsProcessed(count);
            add(volumeMedicalCareResponse);

            volumeMedicalCareResponseRecords.add(new VolumeMedicalCareResponseRecord(departmentRequest,
                    volumeMedicalCareResponse,
                    errCode, errMess));
            count++;

        }

        volumeMedicalCareResponseRecordService.addAll(volumeMedicalCareResponseRecords);
        volumeMedicalCareResponse.setDepartments(volumeMedicalCareResponseRecords);
        volumeMedicalCareResponse.setNumberRecordsProcessed(count);
        volumeMedicalCareResponse.setReqID(volumeMedicalCareRequest.getReqID());
        add(volumeMedicalCareResponse);

        return volumeMedicalCareResponse;
    }
}
