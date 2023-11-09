package ru.hardy.udio.repo.apirepo.padatapatientsrepo.mo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.padatapatients.mo.PADataPatientRequestRecord;
import ru.hardy.udio.domain.struct.People;

import java.util.List;

@Repository
public interface PADataPatientRequestRecordRepo extends JpaRepository<PADataPatientRequestRecord, Long> {

    @Query("select pa from PADataPatientRequestRecord pa inner join pa.patient p where p.people = :people")
    List<PADataPatientRequestRecord> findByPeople(People people);
}
