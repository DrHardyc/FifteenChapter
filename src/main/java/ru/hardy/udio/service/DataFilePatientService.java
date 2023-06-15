package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.repo.DataFilePatientRepo;
import ru.hardy.udio.repo.PeopleRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataFilePatientService {
    @Autowired
    private DataFilePatientRepo dataFilePatientRepo;

    @Autowired
    private DataFileService dataFileService;

    @Autowired
    private SexService sexService;

    @Autowired
    private PeopleRepo peopleRepo;

    public List<DataFilePatient> getAll(){
        return dataFilePatientRepo.findAll();
    }

    public boolean searchFromPeople(DataFilePatient dataFilePatient) {
        return peopleRepo.findPeopleByFamIgnoreCaseAndImIgnoreCaseAndOtIgnoreCaseAndDrAndEnp(dataFilePatient.getFam(), dataFilePatient.getIm(),
                dataFilePatient.getOt(), dataFilePatient.getDr(), dataFilePatient.getEnp()) == null;
    }

    public List<DataFilePatient> getNoSearchFromSRZ(){
        return dataFilePatientRepo.getNoSearchFromSRZ();
    }


    public void updateStatus(DataFilePatient dataFilePatient, int status_code){
        switch (status_code){
            case 1 -> dataFilePatient.setSrz_status("успешно добавлен");
            case 2 -> dataFilePatient.setSrz_status("не найден в срз");
            case 3 -> dataFilePatient.setSrz_status("не добавлен");
            case 4 -> dataFilePatient.setSrz_status("найден по енп");
            case 5 -> dataFilePatient.setSrz_status("дубль");
            case 6 -> dataFilePatient.setSrz_status("ошибка поиска срз");
        }
        dataFilePatient.setSrz_status_code(status_code);
        dataFilePatientRepo.save(dataFilePatient);
    }

    public List<DataFilePatient> getAllLoadSuccess(int srz_status_code) {
        return dataFilePatientRepo.findBySrz_status_code(srz_status_code);
    }
}
