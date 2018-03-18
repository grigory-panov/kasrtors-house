package ru.grigory.castorshouse.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.castorshouse.domain.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by grigory on 10.11.15.
 */
public class ImageMapper implements RowMapper<Image> {
    @Override
    public Image mapRow(ResultSet resultSet, int i) throws SQLException {
        Image img = new Image();
        img.setFilename(resultSet.getString("filename"));
        img.setDescription(resultSet.getString("description"));
        img.setHeader(resultSet.getString("header"));
        img.setMd5(resultSet.getString("md5"));
        img.setMd5thumb(resultSet.getString("md5thumb"));
        img.setTag(resultSet.getString("tag"));
        img.setDateAdd(resultSet.getTimestamp("date_add"));
        img.setInClub(resultSet.getBoolean("in_club"));
        return img;
    }
}
