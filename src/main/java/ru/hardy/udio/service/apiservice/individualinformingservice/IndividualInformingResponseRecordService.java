package ru.hardy.udio.service.apiservice.individualinformingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponseRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualinforming.IndividualInformingResponseRecordRepo;

import java.util.List;

@Service
public class IndividualInformingResponseRecordService {

    @Autowired
    private IndividualInformingResponseRecordRepo individualInformingResponseRecordRepo;

    public void addAll(List<IndividualInformingResponseRecord> individualInformingResponseRecords) {
        individualInformingResponseRecordRepo.saveAll(individualInformingResponseRecords);
    }


}
