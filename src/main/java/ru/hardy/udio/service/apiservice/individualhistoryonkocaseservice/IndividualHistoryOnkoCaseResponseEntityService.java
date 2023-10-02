package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponseEntity;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocase.IndividualHistoryOnkoCaseResponseEntityRepo;

@Service
public class IndividualHistoryOnkoCaseResponseEntityService {

    @Autowired
    private IndividualHistoryOnkoCaseResponseEntityRepo individualHistoryOnkoCaseResponseEntityRepo;

    public void add(IndividualHistoryOnkoCaseResponseEntity individualHistoryOnkoCaseResponseEntity){
        individualHistoryOnkoCaseResponseEntityRepo.save(individualHistoryOnkoCaseResponseEntity);
    }
}
