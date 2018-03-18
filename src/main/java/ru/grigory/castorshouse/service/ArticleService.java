package ru.grigory.castorshouse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.grigory.castorshouse.dao.ArticleDao;
import ru.grigory.castorshouse.domain.Article;
import ru.grigory.castorshouse.exception.BusinessException;

import java.util.List;

/**
 * Created by grigory on 20.09.15.
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ArticleService {
    private final static Logger logger = LoggerFactory.getLogger(ArticleService.class);
    @Autowired
    private ArticleDao articleDao;

    public List<Article> getAll() {
        return articleDao.getAll();
    }

    public Article getById(Integer id) {
        return articleDao.getById(id);
    }

    public int addArticle(Article article) {
        if (article.getId() > 0) {
            throw new BusinessException("Add article with id not supported");
        }
        return articleDao.add(article);
    }

    public void updateArticle(Article article) {
        Article old = articleDao.getById(article.getId());
        if (!old.equals(article)) {
            logger.info("article was changed, updating");
            articleDao.update(article);
            logger.info("article was updated");
        } else {
            logger.info("article was not changed");
        }
    }

    public Article getByName(String page) {
        return articleDao.getPage(page);
    }

    public void deleteArticle(Article article) {
        articleDao.delete(article.getId());
    }
}