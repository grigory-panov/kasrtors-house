package ru.grigory.castorshouse.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.castorshouse.domain.Settings;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 18.10.14
 * Time: 0:04
 * To change this template use File | Settings | File Templates.
 */
public class SettingsMapper implements RowMapper<Settings> {
    @Override
    public Settings mapRow(ResultSet resultSet, int i) throws SQLException {
        Settings settings = new Settings();
        settings.setKey(resultSet.getString("key"));
        settings.setValue(resultSet.getString("value"));
        settings.setDescription(resultSet.getString("description"));
        return settings;
    }
}
