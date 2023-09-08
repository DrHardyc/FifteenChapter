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
        return dateBirth != null;
    }

    public boolean checkEmptyInt(int firstIdentified) {
        return firstIdentified < 0 || firstIdentified > 1;
    }

    public boolean checkEmptyString(String str) {
        return str.isEmpty();
    }

    public boolean checkEnp(String enp) {
        return enp.length() == 16;
    }
}
