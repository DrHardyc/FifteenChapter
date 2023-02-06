package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.DataFile;
import ru.hardy.udio.domain.struct.DataFilePatient;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.repo.PeopleRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
@Component
public class PeopleService {
    @Autowired
    private PeopleRepo peopleRepo;

    public List<People> getAll(){
        return peopleRepo.findAll();
    }


    public People searchFromUdio(DataFilePatient dataFilePatient){
        People people = peopleRepo.findPeopleByFamAndImAndOtAndDr(dataFilePatient.getFam().toUpperCase(), dataFilePatient.getIm().toUpperCase(),
                dataFilePatient.getOt().toUpperCase(), dataFilePatient.getDr(), dataFilePatient.getEnp());
        if (people == null){
            people = peopleRepo.findPeopleByEnp(dataFilePatient.getEnp());
        }
        if (people != null){
            return people;
        }
        return null;
    }

    public People searchFromSRZ(DataFilePatient dataFilePatient) throws SQLException {
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();
        ResultSet resultSet = statement.executeQuery("select count(*), p.id from PEOPLE p where p.fam = '" + dataFilePatient.getFam() +
                "' and p.im = '" + dataFilePatient.getIm() + "' and p.ot = '" + dataFilePatient.getOt() +
                "' and SUBSTRING(convert(varchar, p.dr, 23), 1, 10) = '" + dataFilePatient.getDr() +
                "' and p.enp = '" + dataFilePatient.getEnp() + "' group by p.id");

        resultSet.next();
        if (resultSet.getInt(1) > 0) {
            dataFilePatient.setIdsrz(resultSet.getLong(2));
            return new People(dataFilePatient);
        } else {
            resultSet = statement.executeQuery("select count(*), p.id from PEOPLE p where p.enp = '" + dataFilePatient.getEnp() + "' group by p.id");
            if (resultSet.getInt(1) > 0) {
                dataFilePatient.setIdsrz(resultSet.getLong(2));
                return new People(dataFilePatient);
            }
        } return null;
    }

    public People save(People people){
        return peopleRepo.save(people);
    }

    public People update(People peoplesrz){
        People peopleUdio = peopleRepo.findPeopleByFamAndImAndOtAndDr(peoplesrz.getFam().toUpperCase(), peoplesrz.getIm().toUpperCase(),
                peoplesrz.getOt().toUpperCase(), peoplesrz.getDr(), peoplesrz.getEnp());
        peopleUdio.setEnp(peopleUdio.getEnp());
        peopleUdio.setSex(peopleUdio.getSex());
        peopleUdio.setNhistory(peoplesrz.getNhistory());
        peopleUdio.setDr(peoplesrz.getDr());
        peopleUdio.setOt(peoplesrz.getOt());
        peopleUdio.setFam(peopleUdio.getFam());
        peopleUdio.setIm(peopleUdio.getIm());
        peopleUdio.setIdsrz(peopleUdio.getIdsrz());
        return peopleRepo.save(peopleUdio);
    }
}
