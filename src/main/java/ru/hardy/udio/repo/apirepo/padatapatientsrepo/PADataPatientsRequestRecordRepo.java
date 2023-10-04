package ru.hardy.udio.repo.apirepo.padatapatientsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.PADataPatientsRequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.List;

@Repository
public interface PADataPatientsRequestRecordRepo extends JpaRepository<PADataPatientsRequestRecord, Long> {

    @Query("select pa from PADataPatientsRequestRecord pa inner join pa.patient p where p.people = :people")
    List<PADataPatientsRequestRecord> findByPeople(People people);
}
