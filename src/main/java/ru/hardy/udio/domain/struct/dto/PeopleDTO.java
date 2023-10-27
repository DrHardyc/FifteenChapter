package ru.hardy.udio.domain.struct.dto;

import org.springframework.jdbc.core.RowMapper;
import ru.hardy.udio.domain.struct.People;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeopleDTO implements RowMapper<People> {


    @Override
    public People mapRow(ResultSet rs, int rowNum) throws SQLException {
        People people = new People();

        people.setId(rs.getLong("id"));
        people.setSurname(rs.getString("surname"));
        people.setName(rs.getString("name"));
        people.setPatronymic(rs.getString("patronymic"));
        people.setDateBirth(rs.getDate("date_birth"));
        people.setSex(rs.getInt("sex"));
        people.setDateBeg(rs.getDate("date_beg"));
        people.setDateEdit(rs.getDate("date_edit"));
        people.setEnp(rs.getString("enp"));
        people.setDs(rs.getDate("ds"));
        people.setIdsrz(rs.getLong("idsrz"));
        people.setMo_attach(rs.getInt("mo_attach"));

        return people;
    }


}
