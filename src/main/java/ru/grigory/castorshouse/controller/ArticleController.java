package ru.grigory.castorshouse.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.tautua.markdownpapers.Markdown;
import org.tautua.markdownpapers.ast.Document;
import org.tautua.markdownpapers.ast.Visitor;
import org.tautua.markdownpapers.parser.ParseException;
import org.tautua.markdownpapers.parser.Parser;
import ru.grigory.castorshouse.domain.Article;
import ru.grigory.castorshouse.exception.ResourceNotFoundException;
import ru.grigory.castorshouse.service.ArticleService;
import ru.grigory.castorshouse.web.CustomEmitter;
import ru.grigory.castorshouse.web.OnlyTextEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 * Created by grigory on 04.11.15.
 */
@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ModelAttribute("articles")
    public List<Article> populateArticles() {
        return articleService.getAll();
    }


    @RequestMapping(value = "/article/*.html",  method = RequestMethod.GET)
    public String getArticle(HttpServletRequest request, HttpServletResponse response, Model model) {

        try {
            Article article = articleService.getByName(
                    StringUtils.removeStartIgnoreCase(request.getServletPath(), "/article/"));

            Reader in = new StringReader(article.getText());
            Writer out = new StringWriter();

            Visitor v = new CustomEmitter(out);
            Parser parser = new Parser(in);

            Document doc = null;
            try {
                doc = parser.parse();
                doc.accept(v);
            } catch (ParseException e) {
                // do nothing
            }

            article.setText(out.toString());
            model.addAttribute("article", article);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException();
        }
        return "article";
    }

    // admin methods
    @RequestMapping(method = RequestMethod.GET, value = "admin/index.html")
    public String getIndex() {
        return "admin/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "admin/article.html")
    public String getArticle(@RequestParam(required = false, value = "id") Integer id, Model model) {
        Article article = new Article();
        if (id != null) {
            article = articleService.getById(id);
        }
        model.addAttribute("article", article);
        return "admin/article";
    }

    @RequestMapping(method = RequestMethod.GET, value = "article/delete/{id}")
    public String deleteArticle(@PathVariable(value = "id") Integer id, Model model) {
        Article article = articleService.getById(id);
        articleService.deleteArticle(article);
        return "redirect:/admin/index.html";
    }


    @RequestMapping(method = RequestMethod.POST, value = "admin/article.html")
    public String postArticle(@ModelAttribute("article") Article article, BindingResult bindingResult, Model model) {
        if(!article.getPage().endsWith(".html")){
            bindingResult.addError(new ObjectError("page", "Название статьи должно оканчиваться на .html"));
            model.addAttribute("article", article);
            return "admin/article";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("article", article);
            return "admin/article";
        }

        if(article.getId() > 0){
            articleService.updateArticle(article);
        }else {
            articleService.addArticle(article);
        }
        return "redirect:/admin/index.html";
    }


}
