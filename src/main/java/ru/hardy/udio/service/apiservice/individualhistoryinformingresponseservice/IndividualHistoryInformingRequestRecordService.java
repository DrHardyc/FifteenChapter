package ru.hardy.udio.service.apiservice.individualhistoryinformingresponseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingRequestRecord;
import ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo.IndividualHistoryInformingRequestRecordRepo;

@Service
public class IndividualHistoryInformingRequestRecordService {

    @Autowired
    private IndividualHistoryInformingRequestRecordRepo individualHistoryInformingRequestRecordRepo;

    public void add(IndividualHistoryInformingRequestRecord individualHistoryInformingRequestRecord) {
        individualHistoryInformingRequestRecordRepo.save(individualHistoryInformingRequestRecord);
    }
}
