package ru.hardy.udio.test.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.hardy.udio.test.OneParent;

import java.sql.ResultSet;
import java.sql.SQLException;


public class OneParentMapper implements RowMapper<OneParent> {
    public OneParent mapRow(ResultSet rs, int rowNum) {
        OneParent oneParent = new OneParent();
        try {
            //oneParent.setId(rs.getLong("id"));
            oneParent.setName(rs.getString("name"));
            //oneParent.setNumber(rs.getInt("number"));
            //oneParent.setDate(rs.getDate("date"));
            return oneParent;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
