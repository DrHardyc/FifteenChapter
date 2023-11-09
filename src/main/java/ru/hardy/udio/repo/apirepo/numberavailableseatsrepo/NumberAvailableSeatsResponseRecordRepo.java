package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeatsResponseRecord;

@Repository
public interface NumberAvailableSeatsResponseRecordRepo extends JpaRepository<NumberAvailableSeatsResponseRecord, Long> {
}
