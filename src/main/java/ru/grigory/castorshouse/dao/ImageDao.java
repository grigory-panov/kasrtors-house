package ru.grigory.castorshouse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ru.grigory.castorshouse.dao.mapper.ImageMapper;
import ru.grigory.castorshouse.domain.Image;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by grigory on 10.11.15.
 */
@Repository
public class ImageDao {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplete;

    @Autowired
    private void setDataSource(DataSource datasource) {
        namedJdbcTemplete = new NamedParameterJdbcTemplate(datasource);
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    public List<Image> getRange(int offset, int limit) {
        return jdbcTemplate.query("select * from image order by date_add desc offset ? limit ?", new ImageMapper(), offset, limit);
    }

    public Image getImageByTagAndName(String tag, String filename) {
        try {
            return jdbcTemplate.queryForObject("select * from image where tag = ? and filename = ?", new ImageMapper(), tag, filename);
        }catch (EmptyResultDataAccessException ex){
            return null;
        }
    }

    public void add(Image image) {
        jdbcTemplate.update("insert into image(filename, header, description, tag, md5, md5thumb, date_add, in_club) values (?, ?, ?, ?, ?, ?, ?, ?)",
                image.getFilename(), image.getHeader(), image.getDescription(), image.getTag(), image.getMd5(), image.getMd5thumb(), new Date(), image.isInClub());
    }

    public void update(Image image) {
        jdbcTemplate.update("update image set header=?, description=?, in_club=? where filename=? and tag=?",
                image.getHeader(), image.getDescription(), image.isInClub(), image.getFilename(), image.getTag());
    }


    public int getTotalCount() {
        return jdbcTemplate.queryForObject("select count(*) from image", Integer.class);
    }

    public void delete(Image img) {
        jdbcTemplate.update("delete from image where tag=? and filename = ?", img.getTag(), img.getFilename());
    }

    public List<Image> getImagesByTag(String tag, Boolean inClub) {
        if (inClub != null) {
            return jdbcTemplate.query("select * from image where tag = ? and in_club=? order by date_add desc",
                    new ImageMapper(), tag, inClub);
        } else {
            return jdbcTemplate.query("select * from image where tag = ? order by date_add desc",
                    new ImageMapper(), tag);
        }
    }

    public List<Image> getImagesByTag(String tag, Boolean inClub, int offset, int limit) {
        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("limit", limit);
        paramMap.put("offset", offset);
        StringBuilder query = new StringBuilder("select * from image where 1 = 1 ");
        if (inClub != null) {
            paramMap.put("inClub", inClub);
            query.append("and in_club = :inClub ");
        }
        if (!StringUtils.isEmpty(tag)) {
            paramMap.put("tag", tag);
            query.append("and tag = :tag ");
        }else{
            paramMap.put("tag", "other");
            query.append("and tag != :tag ");
        }
        query.append("order by date_add desc offset :offset limit :limit");
        return namedJdbcTemplete.query(query.toString(), paramMap, new ImageMapper());

    }

    public List<Image> getImagesByTags(String[] params, Boolean inClub) {
        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("ids", Arrays.asList(params));
        if (inClub != null) {
            paramMap.put("inClub", inClub);
            return namedJdbcTemplete.query("select * from image where tag in ( :ids ) and in_club = :inClub order by date_add desc",
                    paramMap,
                    new ImageMapper());
        } else {
            return namedJdbcTemplete.query("select * from image where tag in ( :ids ) order by date_add desc",
                    paramMap,
                    new ImageMapper());
        }
    }

    public int getTotalCountByTag(String tag, Boolean inClub) {
        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        StringBuilder query = new StringBuilder("select count(*) from image where 1 = 1 ");
        if (inClub != null) {
            paramMap.put("inClub", inClub);
            query.append("and in_club = :inClub ");
        }
        if (!StringUtils.isEmpty(tag)) {
            paramMap.put("tag", tag);
            query.append("and tag = :tag ");
        }
        return namedJdbcTemplete.queryForObject(query.toString(), paramMap, Integer.class);
    }

    public List<Image> getImagesByFirstPartOfName(String tag, String name) {
        return jdbcTemplate.query("select * from image where tag = ? and filename like ? || '%' order by filename",
                new ImageMapper(), tag, name);
    }
}
