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
import java.util.List;

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
            } else addNew(departmentRequest, codeMO);
        } else {
            NumberAvailableSeats numberAvailableSeatsAft9 = numberAvailableSeatsRepo.
                    findAllByCodeDepAndCodeMOAft9(departmentRequest.getCodeDep(), codeMO, Instant.now().plus(Duration.ofDays(1)));
            if (numberAvailableSeatsAft9 != null)
                update(numberAvailableSeatsAft9, departmentRequest);
            else addNew(departmentRequest, codeMO);
        }
    }

    private void update(NumberAvailableSeats department, NumberAvailableSeatsRequestRecord departmentRequest){
        department.setDepartmentRequest(departmentRequest);
        department.setCodeDep(departmentRequest.getCodeDep());
        department.setNameDep(departmentRequest.getNameDep());
        department.setDateEdit(Date.from(Instant.now()));
        numberAvailableSeatsRepo.save(department);
    }

    private void addNew(NumberAvailableSeatsRequestRecord departmentRequest, int codeMO){
        NumberAvailableSeats department = new NumberAvailableSeats();
        department.setCodeMO(codeMO);
        department.setDepartmentRequest(departmentRequest);
        department.setCodeDep(departmentRequest.getCodeDep());
        department.setNameDep(departmentRequest.getNameDep());
        department.setDateBeg(Date.from(Instant.now()));
        department.setDateEdit(Date.from(Instant.now()));
        numberAvailableSeatsRepo.save(department);
    }
}
