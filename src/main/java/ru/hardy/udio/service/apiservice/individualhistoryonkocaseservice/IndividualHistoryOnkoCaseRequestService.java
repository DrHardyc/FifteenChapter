package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.repo.apirepo.individualhistoryonkocase.IndividualHistoryOnkoCaseRequestRepo;

@Service
public class IndividualHistoryOnkoCaseRequestService {

    @Autowired
    private IndividualHistoryOnkoCaseRequestRepo individualHistoryOnkoCaseRequestRepo;

    public void add(IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest){

        individualHistoryOnkoCaseRequestRepo.save(individualHistoryOnkoCaseRequest);
    }
}
