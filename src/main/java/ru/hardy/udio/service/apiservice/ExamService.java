package ru.hardy.udio.service.apiservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.service.SexService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

@Service
public class ExamService {

    @Autowired
    private SexService sexService;

    public boolean checkMKB(String mkb){
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        try {
            ResultSet resultSet = statement.executeQuery("select count(*) from nsi_med.med_mkb10 mm where " +
                    "mkb_code = '" + mkb + "'");
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean checkSex(int sex){
        return sexService.getById((long) sex) != null;
    }

    public boolean checkEmptyDate(Date dateBirth) {
        return dateBirth == null;
    }

    public boolean checkEmptyString(String str) {
        return str.isEmpty();
    }

    public boolean checkEnp(String enp) {
        return enp.length() != 16;
    }

    public boolean checkSpecialtyDoctorCode(int specialtyDoctorCode) {
        if (specialtyDoctorCode != 0){
            return true;
        }
        else return false;
    }

    public boolean checkResultDispensaryAppointment(int resultDispensaryAppointment) {
        if (resultDispensaryAppointment != 0){
            return true;
        } else return false;
    }
}
