package ru.hardy.udio.service.apiservice.operatingscheduleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequest;
import ru.hardy.udio.repo.apirepo.operatingschedulerepo.OperatingScheduleRequestRepo;

import java.sql.Date;
import java.time.Instant;

@Service
public class OperatingScheduleRequestService {

    @Autowired
    private OperatingScheduleRequestRepo operatingScheduleRequestRepo;

    public void add(OperatingScheduleRequest operatingScheduleRequest) {
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

}
