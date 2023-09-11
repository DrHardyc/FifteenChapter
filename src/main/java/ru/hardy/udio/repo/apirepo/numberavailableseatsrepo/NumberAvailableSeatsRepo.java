package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeats;

@Repository
public interface NumberAvailableSeatsRepo extends JpaRepository<NumberAvailableSeats, Long> {
    NumberAvailableSeats findNumberAvailableSeatsByCodeDepAndNameDepAndCodeMO(int codeDep, String nameDep, int codeMo);
}
