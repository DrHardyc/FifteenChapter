package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeats;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRepo;
import ru.hardy.udio.service.TokenService;

import java.time.Instant;
import java.util.Date;

@Service
public class NumberAvailableSeatsService {

    @Autowired
    private NumberAvailableSeatsRepo numberAvailableSeatsRepo;

    @Autowired
    private TokenService tokenService;

    public void add(NumberAvailableSeatsRequestRecord department, String token){
        int codeMO = tokenService.getCodeMOWithToken(token);
        NumberAvailableSeats numberAvailableSeats = numberAvailableSeatsRepo.
                findNumberAvailableSeatsByCodeDepAndNameDepAndCodeMO(department.getCode(), department.getName(), codeMO);
        if (numberAvailableSeats != null){
            update(numberAvailableSeats, department);
        } else addNew(department, codeMO);

    }

    private void update(NumberAvailableSeats numberAvailableSeats, NumberAvailableSeatsRequestRecord department){
        numberAvailableSeats.setNumberPlacesCurrentDay(department.getNumberPlacesCurrentDay());
        numberAvailableSeats.setNumberPlacesNext10Days(department.getNumberPlacesNext10Days());
        numberAvailableSeats.setEarliestReleaseDate(department.getEarliestReleaseDate());
        numberAvailableSeats.setNumberPlacesAvailableSoon(department.getNumberPlacesAvailableSoon());
        numberAvailableSeats.setDate_edit(Date.from(Instant.now()));
        numberAvailableSeats.setRequest(department.getRequest());
        numberAvailableSeatsRepo.save(numberAvailableSeats);
    }

    private void addNew(NumberAvailableSeatsRequestRecord department, int codeMO){
        NumberAvailableSeats numberAvailableSeats = new NumberAvailableSeats();
        numberAvailableSeats.setCodeMO(codeMO);
        numberAvailableSeats.setCodeDep(department.getCode());
        numberAvailableSeats.setNameDep(department.getName());
        numberAvailableSeats.setNumberPlacesCurrentDay(department.getNumberPlacesCurrentDay());
        numberAvailableSeats.setNumberPlacesNext10Days(department.getNumberPlacesNext10Days());
        numberAvailableSeats.setEarliestReleaseDate(department.getEarliestReleaseDate());
        numberAvailableSeats.setNumberPlacesAvailableSoon(department.getNumberPlacesAvailableSoon());
        numberAvailableSeats.setDate_beg(Date.from(Instant.now()));
        numberAvailableSeats.setDate_edit(Date.from(Instant.now()));
        numberAvailableSeats.setRequest(department.getRequest());
        numberAvailableSeatsRepo.save(numberAvailableSeats);
    }
}
