package ru.hardy.udio.repo.apirepo.numberavailableseatsrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hardy.udio.domain.api.numberavailableseats.DateNumberVacantPlacesActual;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeats;

import java.util.Date;
import java.util.List;

@Repository
public interface DateNumberVacantPlacesActualRepo extends JpaRepository<DateNumberVacantPlacesActual, Long> {

    DateNumberVacantPlacesActual findByDateVacantAndNumberAvailableSeats(Date date, NumberAvailableSeats numberAvailableSeats);

    List<DateNumberVacantPlacesActual> findByNumberAvailableSeats(NumberAvailableSeats numberAvailableSeats);
}
