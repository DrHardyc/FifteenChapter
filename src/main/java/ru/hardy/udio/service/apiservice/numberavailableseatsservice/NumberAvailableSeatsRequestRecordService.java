package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRequestRecordRepo;

import java.util.List;

@Service
public class NumberAvailableSeatsRequestRecordService {

    @Autowired
    private NumberAvailableSeatsRequestRecordRepo numberAvailableSeatsRequestRecordRepo;

    public void addAll(List<NumberAvailableSeatsRequestRecord> departments) {
        numberAvailableSeatsRequestRecordRepo.saveAll(departments);
    }

    public void add(NumberAvailableSeatsRequestRecord departments){
        numberAvailableSeatsRequestRecordRepo.save(departments);
    }
}
