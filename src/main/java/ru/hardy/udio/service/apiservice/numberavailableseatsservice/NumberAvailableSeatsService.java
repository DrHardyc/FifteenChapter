package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.DateNumberVacantPlaces;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeats;
import ru.hardy.udio.domain.api.numberavailableseats.NumberAvailableSeatsRequestRecord;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.NumberAvailableSeatsRepo;
import ru.hardy.udio.service.TokenService;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class NumberAvailableSeatsService {

    @Autowired
    private NumberAvailableSeatsRepo numberAvailableSeatsRepo;

    public void add(NumberAvailableSeatsRequestRecord departmentRequest, int codeMo){
        NumberAvailableSeats numberAvailableSeats = numberAvailableSeatsRepo.
                findNumberAvailableSeatsByCodeDepAndNameDep(departmentRequest.getCodeDep(), departmentRequest.getNameDep());
        if (numberAvailableSeats != null){
            update(numberAvailableSeats, departmentRequest);
        } else addNew(departmentRequest, codeMo);
    }

    private void update(NumberAvailableSeats department, NumberAvailableSeatsRequestRecord departmentRequest){
        department.setDepartmentRequest(departmentRequest);
        department.setCodeDep(departmentRequest.getCodeDep());
        department.setNameDep(departmentRequest.getNameDep());
        department.setDate_edit(Date.from(Instant.now()));
        numberAvailableSeatsRepo.save(department);
    }

    private void addNew(NumberAvailableSeatsRequestRecord departmentRequest, int codeMO){
        NumberAvailableSeats department = new NumberAvailableSeats();
        department.setCodeMO(codeMO);
        department.setDepartmentRequest(departmentRequest);
        department.setCodeDep(departmentRequest.getCodeDep());
        department.setNameDep(departmentRequest.getNameDep());
        department.setDate_beg(Date.from(Instant.now()));
        department.setDate_edit(Date.from(Instant.now()));
        numberAvailableSeatsRepo.save(department);


    }
}
