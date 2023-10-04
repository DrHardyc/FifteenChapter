package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponse;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponseRecord;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsResponseRepo;
import ru.hardy.udio.service.TokenService;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NumberAvailableSeatsResponseService {

    @Autowired
    private NumberAvailableSeatsResponseRepo numberAvailableSeatsResponseRepo;

    @Autowired
    private NumberAvailableSeatsResponseRecordService numberAvailableSeatsResponseRecordService;

    @Autowired
    private NumberAvailableSeatsService numberAvailableSeatsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private NumberAvailableSeatsRequestRecordService numberAvailableSeatsRequestRecordService;

    public void add(NumberAvailableSeatsResponse numberAvailableSeatsResponse){
        numberAvailableSeatsResponseRepo.save(numberAvailableSeatsResponse);
    }

    public NumberAvailableSeatsResponse processing(NumberAvailableSeatsRequest numberAvailableSeatsRequest,
                             NumberAvailableSeatsResponse numberAvailableSeatsResponse, int codeMO) {
        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<NumberAvailableSeatsResponseRecord> numberAvailableSeatsResponseRecords = new ArrayList<>();

        numberAvailableSeatsRequest.getDepartments().forEach(department ->
                department.setRequest(numberAvailableSeatsRequest));
        numberAvailableSeatsRequestRecordService.addAll(numberAvailableSeatsRequest.getDepartments());

        for (NumberAvailableSeatsRequestRecord departmentRequest : numberAvailableSeatsRequest.getDepartments()){
            departmentRequest.setDate_beg(Date.from(Instant.now()));
            departmentRequest.setDate_edit(Date.from(Instant.now()));

            numberAvailableSeatsService.add(departmentRequest, codeMO);
            numberAvailableSeatsResponse.setNumberRecordsProcessed(count);
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
