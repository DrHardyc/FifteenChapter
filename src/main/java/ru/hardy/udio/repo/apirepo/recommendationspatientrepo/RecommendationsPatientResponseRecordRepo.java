package ru.hardy.udio.repo.apirepo.recommendationspatientrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.recommendationspatient.mo.RecommendationsPatientResponseRecord;

@Repository
public interface RecommendationsPatientResponseRecordRepo extends JpaRepository<RecommendationsPatientResponseRecord, Long> {
}
