package ru.hardy.udio.service.apiservice.individualhistoryonkocaseservice;

import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseRequest;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponse;
import ru.hardy.udio.domain.api.individualhistoryonkocase.IndividualHistoryOnkoCaseResponseRecord;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndividualHistoryOnkoCaseResponseService {

    public IndividualHistoryOnkoCaseResponse processind(IndividualHistoryOnkoCaseRequest individualHistoryOnkoCaseRequest){
        IndividualHistoryOnkoCaseResponse individualHistoryOnkoCaseResponse = new IndividualHistoryOnkoCaseResponse();
        List<IndividualHistoryOnkoCaseResponseRecord> individualHistoryOnkoCaseResponseRecords = new ArrayList<>();

        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getBars();
        try {
            ResultSet resultSet = statement.executeQuery("");
            while (resultSet.next()){
                individualHistoryOnkoCaseResponse.setCodeMO(resultSet.getInt(1));
                individualHistoryOnkoCaseResponse.setCodeMOAttach(resultSet.getInt(2));
                individualHistoryOnkoCaseResponseRecords.add(new IndividualHistoryOnkoCaseResponseRecord(
                        resultSet.getString(3), resultSet.getDate(4), resultSet.getString(5),
                        resultSet.getDate(6), resultSet.getDate(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getString(10), resultSet.getString(11),
                        resultSet.getString(11)
                ));
            }
            individualHistoryOnkoCaseResponse.setIndividualHistoryOnkoCaseResponseRecords(individualHistoryOnkoCaseResponseRecords);
            individualHistoryOnkoCaseResponse.setSetResultRequestCode(200);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return individualHistoryOnkoCaseResponse;
    }
}
