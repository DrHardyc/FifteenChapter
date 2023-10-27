package ru.hardy.udio.service.apiservice.volumemedicalcareservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.volumemedicalcare.*;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.volumemedicalcarerepo.VolumeMedicalCareResponseRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class VolumeMedicalCareResponseService implements APIResponseServiceInterface {

    @Autowired
    private VolumeMedicalCareResponseRepo volumeMedicalCareRequestRepo;

    @Autowired
    private VolumeMedicalCareService volumeMedicalCareService;

    @Autowired
    private VolumeMedicalCareResponseRecordService volumeMedicalCareResponseRecordService;


    public VolumeMedicalCareResponse getWithReqId(String reqID, int codeMO) {
        return volumeMedicalCareRequestRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }

    @Override
    public void add(APIResponse apiResponse) {
        apiResponse.setDateBeg(Date.from(Instant.now()));
        apiResponse.setDateEdit(Date.from(Instant.now()));
        volumeMedicalCareRequestRepo.save((VolumeMedicalCareResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        volumeMedicalCareRequestRepo.saveAll(Collections.singletonList((VolumeMedicalCareResponse) apiResponses));
    }

    @Override
    public VolumeMedicalCareResponse processing(APIRequest apiRequest, APIResponse apiResponse,
                                                MedicalOrganization medicalOrganization) {
        VolumeMedicalCareRequest volumeMedicalCareRequest = (VolumeMedicalCareRequest) apiRequest;
        VolumeMedicalCareResponse volumeMedicalCareResponse = (VolumeMedicalCareResponse) apiResponse;

        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<VolumeMedicalCareResponseRecord> volumeMedicalCareResponseRecords = new ArrayList<>();

        for (VolumeMedicalCareRequestRecord departmentRequest : volumeMedicalCareRequest.getDepartments()){
            if (Integer.parseInt(new SimpleDateFormat("HH").format(Date.from(Instant.now()))) < 9) {
                VolumeMedicalCare volumeMedicalCareBef9 = volumeMedicalCareService.
                        getByAllCodeDepAndCodeMOBef9(departmentRequest
                                .getCodeDep(), medicalOrganization.getCodeMO(), Instant.now().minus(Duration.ofDays(1)));
                if (volumeMedicalCareBef9 != null) {
                    volumeMedicalCareService.update(volumeMedicalCareBef9, departmentRequest);
                    errMess = "Запись успешно обновлена";
                } else volumeMedicalCareService.add(departmentRequest, medicalOrganization.getCodeMO());
            } else {
                VolumeMedicalCare volumeMedicalCareAft9 = volumeMedicalCareService.
                        getAllByCodeDepAndCodeMOAft9(departmentRequest
                                .getCodeDep(), medicalOrganization.getCodeMO(), Instant.now().plus(Duration.ofDays(1)));
                if (volumeMedicalCareAft9 != null) {
                    volumeMedicalCareService.update(volumeMedicalCareAft9, departmentRequest);
                    errMess = "Запись успешно обновлена";
                } else volumeMedicalCareService.add(departmentRequest, medicalOrganization.getCodeMO());
            }

            volumeMedicalCareResponse.setNumberRecordsProcessed(count);
            volumeMedicalCareResponse.setResultResponseCode(200);
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
