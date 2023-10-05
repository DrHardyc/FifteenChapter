package ru.hardy.udio.service.apiservice.individualinformingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.individualhistoryinformingresponserepo.IndividualInformingRequestRecordRepo;

import java.util.List;

@Service
public class IndividualInformingRequestRecordService {

    @Autowired
    private IndividualInformingRequestRecordRepo individualInformingRequestRecordRepo;

    public List<IndividualInformingRequestRecord> getAllByPeople(People people) {
        return individualInformingRequestRecordRepo.findAllByPeople(people);
    }

    public void add(IndividualInformingRequest individualInformingRequest, People people) {


    }
}
