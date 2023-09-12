package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeats;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;

@Repository
public interface NumberAvailableSeatsRepo extends JpaRepository<NumberAvailableSeats, Long> {
    NumberAvailableSeats findNumberAvailableSeatsByCodeDepAndNameDep(int codeDep, String nameDep);
}
