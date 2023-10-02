package ru.hardy.udio.service.apiservice.operatingscheduleservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleResponseRecord;
import ru.hardy.udio.repo.apirepo.operatingschedulerepo.OperatingScheduleResponseRecordRepo;

import java.util.List;

@Service
public class OperatingScheduleResponseRecordService {

    @Autowired
    private OperatingScheduleResponseRecordRepo operatingScheduleResponseRecordRepo;


    public void addAll(List<OperatingScheduleResponseRecord> operatingScheduleResponseRecords) {
        operatingScheduleResponseRecordRepo.saveAll(operatingScheduleResponseRecords);
    }
}
