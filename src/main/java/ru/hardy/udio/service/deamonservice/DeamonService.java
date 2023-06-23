package ru.hardy.udio.service.deamonservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.deamon.Deamon;
import ru.hardy.udio.repo.DeamonRepo;

import java.util.List;

@Service
@Component
public class DeamonService {

    @Autowired
    private DeamonRepo deamonRepo;

    public void update(Deamon deamon){
        deamonRepo.save(deamon);
    }

    public List<Deamon> getAll(){
        return deamonRepo.findAll();
    }
}
