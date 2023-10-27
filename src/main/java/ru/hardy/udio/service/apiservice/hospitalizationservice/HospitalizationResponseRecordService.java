package ru.hardy.udio.service.apiservice.hospitalizationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.hospitalization.HospitalizationResponseRecord;
import ru.hardy.udio.repo.apirepo.hospitalizationrepo.HospitalizationResponseRecordRepo;

import java.util.List;

@Service
public class HospitalizationResponseRecordService {
    @Autowired
    private HospitalizationResponseRecordRepo hospitalizationResponseRecordRepo;

    public void addAll(List<HospitalizationResponseRecord> hospitalizationResponseRecords){
        hospitalizationResponseRecordRepo.saveAll(hospitalizationResponseRecords);
    }
}
