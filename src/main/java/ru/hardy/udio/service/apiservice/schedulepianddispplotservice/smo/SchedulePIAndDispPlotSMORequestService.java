package ru.hardy.udio.service.apiservice.schedulepianddispplotservice.smo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.schedulepianddispplot.smo.SchedulePIAndDispPlotSMORequest;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.smo.SchedulePIAndDispPlotSMORequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

@Service
public class SchedulePIAndDispPlotSMORequestService implements APIRequestServiceInterface {
    @Autowired
    private SchedulePIAndDispPlotSMORequestRepo schedulePIAndDispPlotSMORequestRepo;

    @Override
    public void add(APIRequest apiRequest) {
        schedulePIAndDispPlotSMORequestRepo.save((SchedulePIAndDispPlotSMORequest) apiRequest);
    }

    @Override
    public boolean checkChild(APIRequest request) {
        return true;
    }
}
