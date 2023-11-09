package ru.hardy.udio.service.apiservice.numberavailableseatsservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.numberavailableseats.smo.NumberAvailableSeatsSMORequest;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.smo.NumberAvailableSeatsSMORequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

@Service
public class NumberAvailableSeatsSMORequestService implements APIRequestServiceInterface {

    @Autowired
    private NumberAvailableSeatsSMORequestRepo numberAvailableSeatsSMORequestRepo;


    @Override
    public void add(APIRequest apiRequest) {
        numberAvailableSeatsSMORequestRepo.save((NumberAvailableSeatsSMORequest) apiRequest);
    }

    @Override
    public boolean checkChild(APIRequest request) {
        return true;
    }
}
