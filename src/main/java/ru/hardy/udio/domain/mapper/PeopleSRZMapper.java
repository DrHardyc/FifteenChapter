package ru.hardy.udio.domain.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.hardy.udio.domain.struct.People;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;

public class PeopleSRZMapper implements RowMapper<People> {


    public People mapRow(ResultSet rs, int rowNum) throws SQLException {
        People people = new People();

        people.setSurname(rs.getString("fam"));
        people.setName(rs.getString("im"));
        people.setPatronymic(rs.getString("ot"));
        people.setDateBirth(rs.getDate("dr"));
        people.setEnp(rs.getString("enp"));
        people.setSex(rs.getInt("w"));
        people.setIdsrz(rs.getLong("id"));
        people.setDs(rs.getDate("ds"));
        try{
            people.setMo_attach(rs.getInt("lpu"));
        } catch (Exception e){
            people.setMo_attach(0);
        }

        people.setDateBeg(Date.from(Instant.now()));
        people.setDateEdit(Date.from(Instant.now()));

        return people;
    }

}
