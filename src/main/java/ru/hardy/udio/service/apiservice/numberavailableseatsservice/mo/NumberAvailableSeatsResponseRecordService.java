package ru.hardy.udio.service.apiservice.numberavailableseatsservice.mo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsResponseRecord;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsResponseRecordRepo;

import java.util.List;

@Service
public class NumberAvailableSeatsResponseRecordService {

    @Autowired
    private NumberAvailableSeatsResponseRecordRepo numberAvailableSeatsResponseRecordRepo;

    public void addAll(List<NumberAvailableSeatsResponseRecord> numberAvailableSeatsResponseRecords){
        numberAvailableSeatsResponseRecordRepo.saveAll(numberAvailableSeatsResponseRecords);
    }
}
