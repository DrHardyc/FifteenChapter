package ru.hardy.udio.service.apiservice.individualhistoryinformingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualhistoryinforming.IndividualHistoryInforming;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo.IndividualHistoryInformingRepo;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class IndividualHistoryInformingService {

    @Autowired
    private IndividualHistoryInformingRepo individualHistoryInformingRepo;

    public void addAll(List<IndividualHistoryInforming> individualHistoryInformings){
        individualHistoryInformingRepo.saveAll(individualHistoryInformings);

    }

    public IndividualHistoryInforming getByPeople(People people) {
        return individualHistoryInformingRepo.findByPeople(people);
    }

    public void add(IndividualHistoryInforming individualHistoryInforming) {
        individualHistoryInformingRepo.save(individualHistoryInforming);
    }

    public void update(IndividualHistoryInforming individualHistoryInforming) {
        individualHistoryInforming.setDateEdit(Date.from(Instant.now()));
        individualHistoryInformingRepo.save(individualHistoryInforming);
    }
}
