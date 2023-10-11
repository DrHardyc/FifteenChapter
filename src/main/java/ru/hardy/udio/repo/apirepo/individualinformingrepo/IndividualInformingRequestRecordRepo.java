package ru.hardy.udio.repo.apirepo.individualinformingrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.individualinforming.IndividualInformingRequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.List;

@Repository
public interface IndividualInformingRequestRecordRepo extends JpaRepository<IndividualInformingRequestRecord, Long> {
    @Query("select iirr from IndividualInformingRequestRecord iirr join iirr.patient p where p.people = :people")
    List<IndividualInformingRequestRecord> findAllByPeople(People people);
}
