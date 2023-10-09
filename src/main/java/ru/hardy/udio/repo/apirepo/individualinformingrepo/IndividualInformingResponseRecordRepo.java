package ru.hardy.udio.repo.apirepo.individualinformingrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponseRecord;

@Repository
public interface IndividualInformingResponseRecordRepo extends JpaRepository<IndividualInformingResponseRecord, Long> {

}
