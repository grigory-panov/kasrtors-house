package ru.grigory.castorshouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.grigory.castorshouse.dao.SettingsDao;
import ru.grigory.castorshouse.domain.Settings;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gr
 * Date: 18.10.14
 * Time: 0:44
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class SettingsService {
    @Autowired
    private SettingsDao settingsDao;

    public List<Settings> findAll() {
        return settingsDao.findAll();
    }

    public Map<String, String> findAllAsMap() {
        return settingsDao.findAllAsMap();
    }

    public Settings findByKey(String key) {
        return settingsDao.findByKey(key);
    }

    @Transactional(readOnly = false)
    public void update(Settings settings) {
        settingsDao.update(settings);
    }
}

