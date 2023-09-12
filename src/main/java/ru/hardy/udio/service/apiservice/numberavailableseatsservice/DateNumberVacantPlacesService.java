package ru.hardy.udio.service.apiservice.numberavailableseatsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.numberavailableseats.DateNumberVacantPlaces;
import ru.hardy.udio.repo.apirepo.numberavailableseatsrepo.DateNumberVacantPlacesRepo;

import java.util.List;

@Service
public class DateNumberVacantPlacesService {

    @Autowired
    private DateNumberVacantPlacesRepo dateNumberVacantPlacesRepo;

    public void addAll(List<DateNumberVacantPlaces> dateNumberVacantPlaces){
            dateNumberVacantPlacesRepo.saveAll(dateNumberVacantPlaces);
    }
}
