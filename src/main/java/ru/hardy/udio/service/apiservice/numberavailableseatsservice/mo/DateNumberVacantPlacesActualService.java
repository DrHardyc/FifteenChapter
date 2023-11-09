package ru.hardy.udio.service.apiservice.numberavailableseatsservice.mo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.mo.DateNumberVacantPlacesActual;
import ru.hardy.udio.domain.api.numberavailableseats.mo.NumberAvailableSeats;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.DateNumberVacantPlacesActualRepo;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class DateNumberVacantPlacesActualService {

    @Autowired
    private DateNumberVacantPlacesActualRepo dateNumberVacantPlacesActualRepo;

    public void addAll(NumberAvailableSeats numberAvailableSeats){
        numberAvailableSeats.getRequestRecord().getDateNumberVacantPlaces().forEach(dateNumberVacantPlaces -> {
            DateNumberVacantPlacesActual dateNumberVacantPlacesActualFromDB =
                    dateNumberVacantPlacesActualRepo.findByDateVacantAndNumberAvailableSeats(
                            dateNumberVacantPlaces.getEarliestReleaseDate(), numberAvailableSeats);
            if (dateNumberVacantPlacesActualFromDB != null){
                dateNumberVacantPlacesActualFromDB.setNumberVacant(dateNumberVacantPlaces.getNumberPlacesAvailableSoon());
                dateNumberVacantPlacesActualFromDB.setDateEdit(Date.from(Instant.now()));
                dateNumberVacantPlacesActualRepo.save(dateNumberVacantPlacesActualFromDB);
            } else {
                DateNumberVacantPlacesActual dateNumberVacantPlacesActualNew = new DateNumberVacantPlacesActual();
                dateNumberVacantPlacesActualNew.setDateBeg(Date.from(Instant.now()));
                dateNumberVacantPlacesActualNew.setDateEdit(Date.from(Instant.now()));
                dateNumberVacantPlacesActualNew.setNumberAvailableSeats(numberAvailableSeats);
                dateNumberVacantPlacesActualNew.setDateVacant(dateNumberVacantPlaces.getEarliestReleaseDate());
                dateNumberVacantPlacesActualNew.setNumberVacant(dateNumberVacantPlaces.getNumberPlacesAvailableSoon());
                dateNumberVacantPlacesActualRepo.save(dateNumberVacantPlacesActualNew);
            }
        });
    }

    public List<DateNumberVacantPlacesActual> getAllByNumberAvailableSeats(NumberAvailableSeats numberAvailableSeats) {
        return dateNumberVacantPlacesActualRepo.findByNumberAvailableSeats(numberAvailableSeats);
    }
}
