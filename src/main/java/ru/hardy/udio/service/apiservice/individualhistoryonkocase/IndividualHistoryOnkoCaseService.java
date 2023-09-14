package ru.hardy.udio.service.apiservice.individualhistoryonkocase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCase;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocase.IndividualHistoryOnkoCaseRepo;

@Service
public class IndividualHistoryOnkoCaseService {


    @Autowired
    private IndividualHistoryOnkoCaseRepo individualHistoryOnkoCaseRepo;

    public void add(IndividualHistoryOnkoCase individualHistoryOnkoCase){
        individualHistoryOnkoCaseRepo.save(individualHistoryOnkoCase);
    }
}
