package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.abstractclasses.APIResponse;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponse;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponseRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsResponseRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIResponseServiceInterface;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class NumberAvailableSeatsResponseService implements APIResponseServiceInterface {

    @Autowired
    private NumberAvailableSeatsResponseRepo numberAvailableSeatsResponseRepo;

    @Autowired
    private NumberAvailableSeatsResponseRecordService numberAvailableSeatsResponseRecordService;

    @Autowired
    private NumberAvailableSeatsService numberAvailableSeatsService;

    @Autowired
    private NumberAvailableSeatsRequestRecordService numberAvailableSeatsRequestRecordService;

    @Override
    public void add(APIResponse apiResponse){
        apiResponse.setDateBeg(Date.from(Instant.now()));
        apiResponse.setDateEdit(Date.from(Instant.now()));
        numberAvailableSeatsResponseRepo.save((NumberAvailableSeatsResponse) apiResponse);
    }

    @Override
    public void addAll(List<APIResponse> apiResponses) {
        numberAvailableSeatsResponseRepo.saveAll(Collections.singletonList((NumberAvailableSeatsResponse) apiResponses));
    }

    @Override
    public NumberAvailableSeatsResponse processing(APIRequest apiRequest,
                                                   APIResponse apiResponse, MedicalOrganization medicalOrganization) {
        NumberAvailableSeatsRequest numberAvailableSeatsRequest = (NumberAvailableSeatsRequest) apiRequest;
        NumberAvailableSeatsResponse numberAvailableSeatsResponse = (NumberAvailableSeatsResponse) apiResponse;

        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<NumberAvailableSeatsResponseRecord> numberAvailableSeatsResponseRecords = new ArrayList<>();

        numberAvailableSeatsRequest.getDepartments().forEach(department ->
                department.setRequest(numberAvailableSeatsRequest));
        numberAvailableSeatsRequestRecordService.addAll(numberAvailableSeatsRequest.getDepartments());

        for (NumberAvailableSeatsRequestRecord departmentRequest : numberAvailableSeatsRequest.getDepartments()){
            departmentRequest.setDateBeg(Date.from(Instant.now()));
            departmentRequest.setDateEdit(Date.from(Instant.now()));

            numberAvailableSeatsService.add(departmentRequest, medicalOrganization);
            numberAvailableSeatsResponse.setNumberRecordsProcessed(count);
            numberAvailableSeatsResponse.setResultResponseCode(200);
            add(numberAvailableSeatsResponse);

            numberAvailableSeatsResponseRecords.add(new NumberAvailableSeatsResponseRecord(departmentRequest,
                    numberAvailableSeatsResponse,
                    errCode, errMess));
            count++;

        }

        numberAvailableSeatsResponseRecordService.addAll(numberAvailableSeatsResponseRecords);
        numberAvailableSeatsResponse.setDepartmentResponse(numberAvailableSeatsResponseRecords);
        numberAvailableSeatsResponse.setNumberRecordsProcessed(count);
        numberAvailableSeatsResponse.setReqID(numberAvailableSeatsRequest.getReqID());
        add(numberAvailableSeatsResponse);

        return numberAvailableSeatsResponse;
    }

    public NumberAvailableSeatsResponse getWithReqId(String reqID, int codeMO) {
        return numberAvailableSeatsResponseRepo.findByReqIDAndCodeMO(reqID, codeMO);
    }


}
