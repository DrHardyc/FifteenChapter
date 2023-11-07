package ru.hardy.udio.service.apiservice.numberavailableseatsservice.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.DTO.NumberAvailableSeatsDTO;
import ru.hardy.udio.domain.nsi.MedicalOrganization;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.DateNumberVacantPlacesActualService;
import ru.hardy.udio.service.apiservice.numberavailableseatsservice.NumberAvailableSeatsService;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NumberAvailableSeatsDTOService {

    @Autowired
    private NumberAvailableSeatsService numberAvailableSeatsService;

    @Autowired
    private DateNumberVacantPlacesActualService dateNumberVacantPlacesActualService;

    public List<NumberAvailableSeatsDTO> getAllByMO(MedicalOrganization medicalOrganization){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<NumberAvailableSeatsDTO> numberAvailableSeatsDTOS = new ArrayList<>();

        numberAvailableSeatsService.getAllByMO(medicalOrganization).forEach(numberAvailableSeats -> {
            NumberAvailableSeatsDTO numberAvailableSeatsDTO = new NumberAvailableSeatsDTO();
            numberAvailableSeatsDTO.setName(numberAvailableSeats.getRequestRecord().getNameDep());
            numberAvailableSeatsDTO.setCurrentDay(numberAvailableSeats.getRequestRecord().getNumberPlacesCurrentDay());
            dateNumberVacantPlacesActualService.getAllByNumberAvailableSeats(numberAvailableSeats).forEach(dateNumberVacantPlacesActual -> {
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(1, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay1(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(2, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay2(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(3, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay3(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(4, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay4(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(5, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay5(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(6, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay6(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(7, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay7(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(8, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay8(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(9, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay9(dateNumberVacantPlacesActual.getNumberVacant());
                }
                if (simpleDateFormat
                        .format(dateNumberVacantPlacesActual.getDateVacant())
                        .equals(simpleDateFormat.format(Date.from(Instant.now().plus(10, ChronoUnit.DAYS))))) {
                    numberAvailableSeatsDTO.setDay10(dateNumberVacantPlacesActual.getNumberVacant());
                }
            });
            numberAvailableSeatsDTOS.add(numberAvailableSeatsDTO);
        });
        return numberAvailableSeatsDTOS;
    }
}
