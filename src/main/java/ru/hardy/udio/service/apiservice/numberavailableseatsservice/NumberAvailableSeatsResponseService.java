package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponse;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMOResponseRecord;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponse;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsResponseRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsResponseRepo;
import ru.hardy.udio.service.PeopleService;

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
    private NumberAvailableSeatsResponseService numberAvailableSeatsResponseService;

    @Autowired
    private NumberAvailableSeatsService numberAvailableSeatsService;

    public void add(NumberAvailableSeatsResponse numberAvailableSeatsResponse){
        numberAvailableSeatsResponseRepo.save(numberAvailableSeatsResponse);
    }

    public NumberAvailableSeatsResponse getWithReqId(String reqID) {
        return numberAvailableSeatsResponseRepo.findChoosingMOResponseByReqID(reqID);
    }

    public NumberAvailableSeatsResponse processing(NumberAvailableSeatsRequest numberAvailableSeatsRequest,
                             NumberAvailableSeatsResponse numberAvailableSeatsResponse, String token) {
        String errMess = "Запись успешно обработана";
        int errCode = 500;
        int count = 0;
        List<NumberAvailableSeatsResponseRecord> numberAvailableSeatsResponseRecords = new ArrayList<>();

        for (NumberAvailableSeatsRequestRecord department : numberAvailableSeatsRequest.getDepartments()){
            department.setDate_beg(Date.from(Instant.now()));
            department.setDate_edit(Date.from(Instant.now()));
            numberAvailableSeatsResponseRecords.add(new NumberAvailableSeatsResponseRecord(department,
                    numberAvailableSeatsResponse,
                    errCode, errMess));
            count++;

            numberAvailableSeatsService.add(department, token);
            numberAvailableSeatsResponse.setNumberRecordsProcessed(count);
            numberAvailableSeatsResponseService.add(numberAvailableSeatsResponse);
        }


        numberAvailableSeatsResponseRecordService.addAll(numberAvailableSeatsResponseRecords);
        numberAvailableSeatsResponse.setDepartments(numberAvailableSeatsResponseRecords);
        numberAvailableSeatsResponse.setNumberRecordsProcessed(count);
        numberAvailableSeatsResponse.setReqID(numberAvailableSeatsRequest.getReqID());
        add(numberAvailableSeatsResponse);

        return numberAvailableSeatsResponse;
    }
}
