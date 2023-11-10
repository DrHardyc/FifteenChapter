package ru.hardy.udio.service.apiservice.recommendationspatientservice.so;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatient;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientRequestRecord;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMORequestRecord;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.apirepo.recommendationspatientrepo.RecommendationsPatientRepo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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

    public boolean checkPatient(People people, String recommendation, Date dateRecommendation) {
        return recommendationsPatientRepo.findByPeopleAndRequestRecord_RecommendationAndRequestRecord_DateRecommendation(
                people, recommendation, dateRecommendation) != null;
    }

    public List<RecommendationsPatient> getAllByPeople(People people) {
        return recommendationsPatientRepo.findByPeople(people);
    }

    public List<RecommendationsPatient> getAll() {
        return recommendationsPatientRepo.findAll();
    }

    public List<RecommendationsPatient> getAllByPeopleList(List<People> peopleList) {
        List<RecommendationsPatient> recommendationsPatients = new ArrayList<>();
        peopleList.forEach(people -> {
            recommendationsPatients.addAll(recommendationsPatientRepo.findByPeople(people));
        });
        return recommendationsPatients;
    }
}
