package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.repo.DataFileRepo;

@Service
public class DataFileService {

    @Autowired
    private DataFileRepo dataFileRepo;

    public void save(DataFile dataFile){
        dataFileRepo.save(dataFile);
    }
}
