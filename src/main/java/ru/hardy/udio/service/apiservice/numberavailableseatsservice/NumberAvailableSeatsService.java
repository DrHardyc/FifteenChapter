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

    @Autowired
    private TokenService tokenService;

    @Autowired
    private NumberAvailableSeatsRequestRecordService numberAvailableSeatsRequestRecordService;

    public void add(NumberAvailableSeatsRequestRecord departmentRequest, String token){
        int codeMO = tokenService.getCodeMOWithToken(token);
        NumberAvailableSeats numberAvailableSeats = numberAvailableSeatsRepo.
                findNumberAvailableSeatsByCodeDepAndNameDepAndCodeMO(departmentRequest.getCodeMO(),
                        departmentRequest.getNameDep(), codeMO);
        if (numberAvailableSeats != null){
            update(numberAvailableSeats, departmentRequest);
        } else addNew(departmentRequest, codeMO);
    }

    private void update(NumberAvailableSeats department, NumberAvailableSeatsRequestRecord departmentRequest){
        department.setDepartmentRequest(departmentRequest);
        department.setNumberPlacesCurrentDay(departmentRequest.getNumberPlacesCurrentDay());
        department.setNumberPlacesNext10Days(departmentRequest.getNumberPlacesNext10Days());
        department.setDateNumberVacantPlaces(departmentRequest.getDateNumberVacantPlaces());
        department.setDate_edit(Date.from(Instant.now()));
        numberAvailableSeatsRepo.save(department);
    }

    private void addNew(NumberAvailableSeatsRequestRecord departmentRequest, int codeMO){
        NumberAvailableSeats department = new NumberAvailableSeats();
        department.setDepartmentRequest(departmentRequest);
        department.setCodeMO(codeMO);
        department.setCodeDep(departmentRequest.getCodeDep());
        department.setNameDep(departmentRequest.getNameDep());
        department.setNumberPlacesNext10Days(departmentRequest.getNumberPlacesNext10Days());
        department.setNumberPlacesCurrentDay(departmentRequest.getNumberPlacesCurrentDay());

        //получаем отдельную ссылку на объект так как нельзя сохранять ссылку на объект если она уже есть в другой коллекции
        List<DateNumberVacantPlaces> dateNumberVacantPlaces = numberAvailableSeatsRequestRecordService.getById(departmentRequest.getId()).getDateNumberVacantPlaces();
        department.setDateNumberVacantPlaces(dateNumberVacantPlaces);
        numberAvailableSeatsRepo.save(department);
    }
}
