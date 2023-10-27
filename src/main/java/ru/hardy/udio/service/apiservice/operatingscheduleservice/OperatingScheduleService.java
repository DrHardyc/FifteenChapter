package ru.hardy.udio.service.apiservice.operatingscheduleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.operatingschedule.OperatingSchedule;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequestRecord;
import ru.hardy.udio.repo.apirepo.operatingschedulerepo.OperatingScheduleRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class OperatingScheduleService {


    @Autowired
    private OperatingScheduleRepo operatingScheduleRepo;

    public void add(OperatingScheduleRequestRecord operatingScheduleRequestRecord, int codeMO){
        OperatingSchedule operatingScheduleFromDB =
                operatingScheduleRepo.findByCodeMOAndCodeDepAndHolidaysDep(codeMO,
                operatingScheduleRequestRecord.getCodeDep(), operatingScheduleRequestRecord.getHolidaysDep());
        if (operatingScheduleFromDB != null){
            operatingScheduleFromDB.setDate_edit(Date.from(Instant.now()));
            operatingScheduleFromDB.setRequestRecord(operatingScheduleRequestRecord);
            operatingScheduleRepo.save(operatingScheduleFromDB);
        } else {
            OperatingSchedule operatingSchedule = new OperatingSchedule();
            operatingSchedule.setDate_beg(Date.from(Instant.now()));
            operatingSchedule.setDate_edit(Date.from(Instant.now()));
            operatingSchedule.setRequestRecord(operatingScheduleRequestRecord);
            operatingScheduleRepo.save(operatingSchedule);
        }
    }
}
