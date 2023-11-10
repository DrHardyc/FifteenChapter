package ru.hardy.udio.service.apiservice.recommendationspatientservice.so;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientResponseRecord;
import ru.hardy.udio.repo.apirepo.recommendationspatientrepo.RecommendationsPatientResponseRecordRepo;

import java.util.List;

@Service
public class RecommendationsPatientResponseRecordService {
    @Autowired
    private RecommendationsPatientResponseRecordRepo recommendationsPatientResponseRecordRepo;

    public void addAll(List<RecommendationsPatientResponseRecord> recommendationsPatientResponseRecords){
        recommendationsPatientResponseRecordRepo.saveAll(recommendationsPatientResponseRecords);
    }



}
