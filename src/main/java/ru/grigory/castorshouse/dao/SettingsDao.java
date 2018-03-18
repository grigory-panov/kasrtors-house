package ru.grigory.castorshouse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grigory.castorshouse.dao.mapper.SettingsMapper;
import ru.grigory.castorshouse.domain.Settings;
import ru.grigory.castorshouse.exception.SettingsNotFoundException;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 18.10.14
 * Time: 0:06
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class SettingsDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    public Settings findByKey(String key) throws SettingsNotFoundException {
        try {
            return jdbcTemplate.queryForObject("select * from settings where key=?", new SettingsMapper(), key);
        } catch (EmptyResultDataAccessException ex) {
            throw new SettingsNotFoundException("key=" + key);
        }
    }

    public List<Settings> findAll() {
        return jdbcTemplate.query("select * from settings order by key", new SettingsMapper());
    }

    public Map<String, String> findAllAsMap() {
        List<Settings> list = jdbcTemplate.query("select * from settings", new SettingsMapper());
        Map<String, String> result = new HashMap<String, String>(list.size());
        for (Settings s : list) {
            result.put(s.getKey(), s.getValue());
        }
        return result;
    }

    public void update(Settings settings) {
        jdbcTemplate.update("update settings set value=?, description=? where key =?",
                settings.getValue(), settings.getDescription(), settings.getKey());
    }
}
