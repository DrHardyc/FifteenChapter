package ru.hardy.udio.repo.apirepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.ChoosingMO;
import ru.hardy.udio.domain.struct.People;

@Repository
public interface ChoosingMORepo extends JpaRepository<ChoosingMO, Long> {
    ChoosingMO findChoosingMOByPeople(People people);
}
