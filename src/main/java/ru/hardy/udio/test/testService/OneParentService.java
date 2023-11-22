package ru.hardy.udio.test.testService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.hardy.udio.test.OneParent;
import ru.hardy.udio.test.TestPeople;
import ru.hardy.udio.test.mapper.OneParentMapper;
import ru.hardy.udio.test.mapper.TestPeopleMapper;
import ru.hardy.udio.test.testrepo.OneParentRepo;

import java.util.List;

@Component
public class OneParentService {
    @Autowired
    private OneParentRepo oneParentRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insertData() {
        jdbcTemplate.execute("INSERT INTO udio_tfoms.oneparent(name, number, date) VALUES('Victor', 15, now())");
        jdbcTemplate.execute("INSERT INTO udio_tfoms.oneparent(name, number, date) VALUES('Puctor', 18, now())");
        jdbcTemplate.execute("INSERT INTO udio_tfoms.oneparent(name, number, date) VALUES('Доктор', 1, now())");
        jdbcTemplate.execute("INSERT INTO udio_tfoms.oneparent(name, number, date) VALUES('Чпоктор', 3, now())");
        jdbcTemplate.execute("INSERT INTO udio_tfoms.oneparent(name, number, date) VALUES('Трактор', 8, now())");
    }

    public List<OneParent> getAll(){
        return oneParentRepo.findAll();
    }

    public List<OneParent> getAllTemplate(){
        RowMapper<OneParent> oneParentRowMapper = new OneParentMapper();
        return jdbcTemplate.query("select * from udio_tfoms.oneparent", oneParentRowMapper);
    }

    public List<TestPeople> getAllTemplateTestPeople(){
        RowMapper<TestPeople> testPeopleRowMapper = new TestPeopleMapper();
        return jdbcTemplate.query("select * from udio_tfoms.people", testPeopleRowMapper);
    }
}
