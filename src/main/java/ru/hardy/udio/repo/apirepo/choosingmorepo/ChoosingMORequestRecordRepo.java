package ru.hardy.udio.repo.apirepo.choosingmorepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.choosingmo.ChoosingMORequestRecord;

@Repository
public interface ChoosingMORequestRecordRepo extends JpaRepository<ChoosingMORequestRecord, Long> {
}
