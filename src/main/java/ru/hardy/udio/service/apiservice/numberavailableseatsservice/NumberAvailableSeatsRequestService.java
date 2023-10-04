package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequest;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRequestRepo;
import ru.hardy.udio.service.TokenService;

@Service
public class NumberAvailableSeatsRequestService {

    @Autowired
    private NumberAvailableSeatsRequestRepo numberAvailableSeatsRequestRepo;

    @Autowired
    private TokenService tokenService;

    public void add(NumberAvailableSeatsRequest numberAvailableSeatsRequest){
        numberAvailableSeatsRequestRepo.save(numberAvailableSeatsRequest);
    }

}
