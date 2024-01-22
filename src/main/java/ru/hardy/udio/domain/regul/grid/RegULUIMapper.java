package ru.hardy.udio.domain.regul.grid;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegULUIMapper implements RowMapper<RegULUI> {

    @Override
    public RegULUI mapRow(ResultSet rs, int rowNum) throws SQLException {
        RegULUI regULUI = new RegULUI();
        regULUI.setInn(rs.getString("inn"));
        regULUI.setOgrn(rs.getString("ogrn"));
        regULUI.setRegNFoms(rs.getString("regnum"));
        regULUI.setName(rs.getString("name"));
        regULUI.setStatus(rs.getString("status"));
        regULUI.setDate(rs.getDate("date"));
        return regULUI;
    }
}
