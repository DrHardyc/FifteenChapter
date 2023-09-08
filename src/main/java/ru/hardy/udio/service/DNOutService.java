package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hardy.udio.config.DBJDBCConfig;
import ru.hardy.udio.domain.struct.DNOut;
import ru.hardy.udio.domain.struct.dto.DNOutDto;
import ru.hardy.udio.repo.DNOutRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class DNOutService {

    @Autowired
    private DNOutRepo dnOutRepo;

    @Autowired
    private SexService sexService;

    public void add(DNOut dnOut){
        dnOutRepo.save(dnOut);
    }

    public List<DNOutDto> getAll(){
        List<DNOutDto> dnOutDtoDtos = new ArrayList<>();
        DBJDBCConfig dbjdbcConfig = new DBJDBCConfig();
        Statement statement = dbjdbcConfig.getUDIO();
        try {
            ResultSet resultSet = statement.executeQuery("select p.fam, p.im, p.ot, p.dr, p.enp, p.sex_id, p.ds, " +
                    "string_agg(d.diag, ';'), string_agg(to_char(d.date_1, 'dd.mm.yyyy'), ';') " +
                    "from udio_tfoms.people p inner join udio_tfoms.dnout d on p.id = d.people_id " +
                    "group by p.fam, p.im, p.ot, p.dr, p.enp, p.sex_id, p.ds");

            while (resultSet.next()) {
                dnOutDtoDtos.add(new DNOutDto(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4),
                        resultSet.getString(5),
                        sexService.getById(resultSet.getLong(6)),
                        resultSet.getDate(7),
                        resultSet.getString(8),
                        resultSet.getString(9)));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return dnOutDtoDtos;
    }
}
