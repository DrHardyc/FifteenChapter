package ru.hardy.udio.test.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.hardy.udio.test.TestPeople;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestPeopleMapper implements RowMapper<TestPeople> {

    public TestPeople mapRow(ResultSet rs, int rowNum) {
        TestPeople testPeople = new TestPeople();
        try {
            testPeople.setId(rs.getLong("id"));
            testPeople.setName(rs.getString("name"));
            testPeople.setSurname(rs.getString("surname"));
            testPeople.setPatronymic(rs.getString("patronymic"));
            testPeople.setDateBirth(rs.getDate("date_birth"));
            return testPeople;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
