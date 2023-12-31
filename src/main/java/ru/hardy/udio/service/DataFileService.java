package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.repo.DataFileRepo;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class DataFileService {

    @Autowired
    private DataFileRepo dataFileRepo;

    public void save(DataFile dataFile){
        for (DataFilePatient dataFilePatient : dataFile.getDataFilePatient()) {
            dataFilePatient.setDate_edit(Date.from(Instant.now()));
        }
        dataFileRepo.save(dataFile);
    }

    public List<DataFile> getAll(){
        return dataFileRepo.findAll();
    }


}
