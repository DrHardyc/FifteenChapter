package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.time.Instant;
import java.util.Date;

@Service
public class NumberAvailableSeatsRequestService implements APIRequestServiceInterface {

    @Autowired
    private NumberAvailableSeatsRequestRepo numberAvailableSeatsRequestRepo;

    public void add(APIRequest apiRequest){
        NumberAvailableSeatsRequest numberAvailableSeatsRequest = (NumberAvailableSeatsRequest) apiRequest;
        numberAvailableSeatsRequest.setDateBeg(Date.from(Instant.now()));
        numberAvailableSeatsRequest.setDateEdit(Date.from(Instant.now()));
        numberAvailableSeatsRequest.getDepartments().forEach(department -> {
            //department.getDepartment().setRequestRecord(department);
            department.setRequest(numberAvailableSeatsRequest);
            department.setDateBeg(Date.from(Instant.now()));
            department.setDateEdit(Date.from(Instant.now()));
        });
        numberAvailableSeatsRequestRepo.save((NumberAvailableSeatsRequest) apiRequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((NumberAvailableSeatsRequest) apiRequest).getDepartments() != null;
    }

}
