package ru.hardy.udio.service.apiservice.operatingscheduleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.abstractclasses.APIRequest;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;
import ru.hardy.udio.repo.apirepo.operatingschedulerepo.OperatingScheduleRequestRepo;
import ru.hardy.udio.service.apiservice.apiinterface.APIRequestServiceInterface;

import java.sql.Date;
import java.time.Instant;

@Service
public class OperatingScheduleRequestService implements APIRequestServiceInterface {

    @Autowired
    private OperatingScheduleRequestRepo operatingScheduleRequestRepo;

    @Override
    public void add(APIRequest apiRequest) {
        OperatingScheduleRequest operatingScheduleRequest = (OperatingScheduleRequest) apiRequest;
        operatingScheduleRequest.setDateBeg(java.util.Date.from(Instant.now()));
        operatingScheduleRequest.setDateEdit(java.util.Date.from(Instant.now()));
        operatingScheduleRequest.getDepartments().forEach(department -> {
            department.setRequest(operatingScheduleRequest);
            department.getCabinets().forEach(cabinet -> {
                cabinet.setRequestRecord(department);
                cabinet.setDateBeg(Date.from(Instant.now()));
                cabinet.setDateEdit(Date.from(Instant.now()));
            });
        });
        operatingScheduleRequestRepo.save(operatingScheduleRequest);
    }

    @Override
    public boolean checkChild(APIRequest apiRequest) {
        return ((OperatingScheduleRequest) apiRequest).getDepartments() != null;
    }
}
