package ru.grigory.castorshouse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.grigory.castorshouse.dao.mapper.ArticleMapper;
import ru.grigory.castorshouse.domain.Article;
import ru.grigory.castorshouse.exception.ArticleNotFoundException;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by grigory on 19.09.15.
 */
@Repository
public class ArticleDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private void setDataSource(DataSource datasource) {
        jdbcTemplate = new JdbcTemplate(datasource);
    }

    public Article getById(int id) {
        try {
            return jdbcTemplate.queryForObject("select * from article where id=?", new ArticleMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ArticleNotFoundException("id=" + id);
        }
    }

    public List<Article> getAll() {
        return jdbcTemplate.query("select * from article order by id desc", new ArticleMapper());
    }

    public int add(Article article) {
        return jdbcTemplate.queryForObject("insert into article (header, text, page) values (?, ?, ?) returning id",
                Integer.class,
                article.getHeader(), article.getText(), article.getPage());
    }

    public void update(Article article) {
        jdbcTemplate.update("update article set header=?, text=?, page=? where id =?",
                article.getHeader(), article.getText(), article.getPage(), article.getId());
    }

    public Article getPage(String page) {
        return jdbcTemplate.queryForObject("select * from article where page = ?", new ArticleMapper(), page);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from article where id = ?", id);
    }
}
