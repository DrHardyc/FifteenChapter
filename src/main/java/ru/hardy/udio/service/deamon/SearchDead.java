package ru.hardy.udio.service.deamon;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.report.DateInterval;
import ru.hardy.udio.domain.struct.People;
import ru.hardy.udio.service.ServiceUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchDead {


    public List<People> search(List<People> peopleList){
        List<People> strPeoples = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getSRZ();
        try {
            for (People people : peopleList) {
                ResultSet resultSet = statement.executeQuery("select p.enp, p.lpu, p.id from PEOPLE p join HISTFDR h on h.pid = p.id " +
                        " where (concat(p.FAM, ' ', p.IM, ' ', p.OT) = '" + people.getFIO() + "'" +
                        " or concat(h.FAM, ' ', h.IM, ' ', h.OT) = '" + people.getFIO() + "')" +
                        " and p.DR  = PARSE('" + dateFormat.format(people.getDr()) + "' as date)" +
                        " and coalesce(p.ds, 0) <> 0");
                if (resultSet.next()){
                    strPeoples.add(people);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return strPeoples;
    }
}
