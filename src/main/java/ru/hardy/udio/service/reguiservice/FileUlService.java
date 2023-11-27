package ru.hardy.udio.service.reguiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.regul.FileUL;
import ru.hardy.udio.repo.regulrepo.FileUlRepo;

@Service
public class FileUlService {

    @Autowired
    private FileUlRepo fileUlRepo;

    public void add(FileUL fileUl){
        fileUlRepo.save(fileUl);
    }
}
