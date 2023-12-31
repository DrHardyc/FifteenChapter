package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeats;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRepo;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class NumberAvailableSeatsService {

    @Autowired
    private NumberAvailableSeatsRepo numberAvailableSeatsRepo;

    public void add(NumberAvailableSeatsRequestRecord departmentRequest, int codeMO){
        if (Integer.parseInt(new SimpleDateFormat("HH").format(Date.from(Instant.now()))) < 9) {
            NumberAvailableSeats numberAvailableSeatsBef9 = numberAvailableSeatsRepo.
                    findByAllCodeDepAndCodeMOBef9(departmentRequest.getCodeDep(), codeMO, Instant.now().minus(Duration.ofDays(1)));
            if (numberAvailableSeatsBef9 != null) {
                update(numberAvailableSeatsBef9, departmentRequest);
            } else addNew(departmentRequest);
        } else {
            NumberAvailableSeats numberAvailableSeatsAft9 = numberAvailableSeatsRepo.
                    findAllByCodeDepAndCodeMOAft9(departmentRequest.getCodeDep(), codeMO, Instant.now().plus(Duration.ofDays(1)));
            if (numberAvailableSeatsAft9 != null)
                update(numberAvailableSeatsAft9, departmentRequest);
            else addNew(departmentRequest);
        }
    }

    private void update(NumberAvailableSeats department, NumberAvailableSeatsRequestRecord departmentRequest){
        department.setRequestRecord(departmentRequest);
        department.setDateEdit(Date.from(Instant.now()));
        numberAvailableSeatsRepo.save(department);
    }

    private void addNew(NumberAvailableSeatsRequestRecord departmentRequest){
        NumberAvailableSeats department = new NumberAvailableSeats();
        department.setRequestRecord(departmentRequest);
        department.setDateBeg(Date.from(Instant.now()));
        department.setDateEdit(Date.from(Instant.now()));
        numberAvailableSeatsRepo.save(department);
    }
}
