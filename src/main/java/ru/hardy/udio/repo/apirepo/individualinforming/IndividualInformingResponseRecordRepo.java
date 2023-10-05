package ru.hardy.udio.repo.apirepo.individualinforming;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequest;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingResponseRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.List;

@Repository
public interface IndividualInformingResponseRecordRepo extends JpaRepository<IndividualInformingResponseRecord, Long> {

}
