package ru.hardy.udio.repo.apirepo.recommendationspatientrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatient;
import ru.hardy.udio.domain.api.recommendationspatient.smo.RecommendationsPatientSMORequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.Date;
import java.util.List;

@Repository
public interface RecommendationsPatientRepo extends JpaRepository<RecommendationsPatient, Long> {
    RecommendationsPatient findByPeopleAndRequestRecord_RecommendationAndRequestRecord_DateRecommendation(People people,
                                                                                                          String recommended,
                                                                                                          Date dateRecommendation);
    List<RecommendationsPatient> findByPeople(People people);

}
