package ru.grigory.castorshouse.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.grigory.castorshouse.domain.Article;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by grigory on 19.09.15.
 */
public class ArticleMapper implements RowMapper<Article> {
    @Override
    public Article mapRow(ResultSet resultSet, int i) throws SQLException {
        Article article = new Article();
        article.setId(resultSet.getInt("id"));
        article.setHeader(resultSet.getString("header"));
        article.setText(resultSet.getString("text"));
        article.setPage(resultSet.getString("page"));
        return article;
    }
}
