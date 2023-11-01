package ru.hardy.udio.service.apiservice.operatingscheduleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.operatingschedule.OperatingScheduleRequestRecord;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.repo.apirepo.operatingschedulerepo.OperatingScheduleRequestRecordRepo;

import java.util.List;

@Service
public class OperatingScheduleRequestRecordService {
    @Autowired
    private OperatingScheduleRequestRecordRepo operatingScheduleRequestRecordRepo;

    public List<OperatingScheduleRequestRecord> getAllByMO(MedicalOrganization medicalOrganization){
        return operatingScheduleRequestRecordRepo.findAllActualByMO(medicalOrganization);
    }
}
