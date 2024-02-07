package ru.hardy.udio.domain.regul.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.hardy.udio.domain.regul.LoadSettings;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadSettingsMapper implements RowMapper<LoadSettings> {
    public LoadSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        LoadSettings loadSettings = new LoadSettings();
        loadSettings.setPathIn(rs.getString("path_in"));
        loadSettings.setPathOut(rs.getString("path_out"));

        return loadSettings;
    }
}
