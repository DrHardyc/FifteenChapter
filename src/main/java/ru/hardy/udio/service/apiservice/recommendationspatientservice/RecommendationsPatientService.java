package ru.hardy.udio.service.apiservice.recommendationspatientservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMO;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatient;
import ru.hardy.udio.domain.api.recommendationspatient.RecommendationsPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.recommendationspatientrepo.RecommendationsPatientRepo;

import java.time.Instant;
import java.util.Date;

@Service
public class RecommendationsPatientService {
    @Autowired
    private RecommendationsPatientRepo recommendationsPatientRepo;

    public void add(People people, RecommendationsPatientRequestRecord recommendationsPatientRequestRecord){
        RecommendationsPatient recommendationsPatient = new RecommendationsPatient();
        recommendationsPatient.setPeople(people);
        recommendationsPatient.setDate_beg(Date.from(Instant.now()));
        recommendationsPatient.setDate_edit(Date.from(Instant.now()));
        recommendationsPatient.setRequestRecord(recommendationsPatientRequestRecord);
        recommendationsPatientRepo.save(recommendationsPatient);
    }

    public boolean checkPatient(People people, String recommendation) {
        return recommendationsPatientRepo.findByPeopleAndRequestRecord_Recommendation(people, recommendation) != null;
    }

}
