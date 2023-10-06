package ru.hardy.udio.service.apiservice.individualhistoryinformingresponseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInformingResponseRecord;
import ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo.IndividualHistoryInformingResponseRecordRepo;

import java.util.List;

@Service
public class IndividualHistoryInformingResponseRecordService{

    @Autowired
    private IndividualHistoryInformingResponseRecordRepo individualHistoryInformingResponseRecordRepo;

    public void addAll(List<IndividualHistoryInformingResponseRecord> individualHistoryInformingResponseRecordEntities){
        individualHistoryInformingResponseRecordRepo.saveAll(individualHistoryInformingResponseRecordEntities);
    }

}