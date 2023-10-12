package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocaserepo.IndividualHistoryOnkoCaseRequestRecordRepo;

import java.util.List;

@Service
public class IndividualHistoryOnkoCaseRequestRecordService {

    @Autowired
    private IndividualHistoryOnkoCaseRequestRecordRepo individualHistoryOnkoCaseRequestRecordRepo;


    public void add(IndividualHistoryOnkoCaseRequestRecord individualHistoryOnkoCaseRequestRecord) {
        individualHistoryOnkoCaseRequestRecordRepo.save(individualHistoryOnkoCaseRequestRecord);
    }

    public List<IndividualHistoryOnkoCaseRequestRecord> getAllByPeople(People people) {
        return individualHistoryOnkoCaseRequestRecordRepo.findAllByPeople(people);
    }
}
