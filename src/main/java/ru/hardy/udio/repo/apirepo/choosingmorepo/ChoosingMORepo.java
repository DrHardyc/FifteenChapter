package ru.hardy.udio.repo.apirepo.choosingmorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMO;
import ru.hardy.udio.domain.struct.People;


@Repository
public interface ChoosingMORepo extends JpaRepository<ChoosingMO, Long> {
    ChoosingMO findChoosingMOByPeople(People people);

    @Query("select t from ChoosingMO t join t.requestRecord rr join rr.request r where t.people = :people and r.medicalOrganization.codeMO = :codeMO")
    ChoosingMO findChoosingMOByPeopleAndCodeMO(People people, int codeMO);
}
