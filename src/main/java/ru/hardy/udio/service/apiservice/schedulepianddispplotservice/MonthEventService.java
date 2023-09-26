package ru.hardy.udio.service.apiservice.schedulepianddispplotservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.api.schedulepianddispplot.MonthEvent;
import ru.hardy.udio.repo.apirepo.schedulepianddispplotrepo.MonthEventRepo;

import java.util.List;

@Service
public class MonthEventService {

    @Autowired
    private MonthEventRepo monthEventRepo;

    public void addAll(List<MonthEvent> monthEvents){
        monthEventRepo.saveAll(monthEvents);

    }
}
