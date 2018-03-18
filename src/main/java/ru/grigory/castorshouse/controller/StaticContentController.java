package ru.grigory.castorshouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tautua.markdownpapers.ast.Document;
import org.tautua.markdownpapers.ast.Visitor;
import org.tautua.markdownpapers.parser.ParseException;
import org.tautua.markdownpapers.parser.Parser;
import ru.grigory.castorshouse.domain.Article;
import ru.grigory.castorshouse.domain.Image;
import ru.grigory.castorshouse.service.ArticleService;
import ru.grigory.castorshouse.service.ImageService;
import ru.grigory.castorshouse.web.OnlyTextEmitter;
import ru.grigory.castorshouse.web.Page;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by grigory on 11.09.15.
 */
@Controller
public class StaticContentController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ImageService imageService;

    private static final int pageSize = 15;


    private String getHtml(String articleName){
        try {
            Article article = articleService.getByName(articleName);

            Reader in = new StringReader(article.getText());
            Writer out = new StringWriter();

            Visitor v = new OnlyTextEmitter(out);
            Parser parser = new Parser(in);

            Document doc = null;
            try {
                doc = parser.parse();
            } catch (ParseException e) {
                return article.getText();
            }
            doc.accept(v);
            return out.toString();
        }catch (EmptyResultDataAccessException e){
            return "<p>Описание не задано</p>";
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "index.html")
    public String getIndex(Model model) {
        model.addAttribute("img_dog", imageService.getRandomImageInTags(true, "cadebou", "heeler", "minibull"));
        model.addAttribute("img_puppy", imageService.getRandomImageInTags(true, "cadebou-puppy", "heeler-puppy", "minibull-puppy"));
        model.addAttribute("about_kennelclub", getHtml("about_kennelclub.html"));
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "heeler.html")
    public String getHeeler(Model model) {
        model.addAttribute("img_heeler", imageService.getRandomImageInTag(true, "heeler"));
        model.addAttribute("img_puppy", imageService.getRandomImageInTag(true, "heeler-puppy"));
        model.addAttribute("about_heeler", getHtml("about_heeler.html"));
        return "heeler";
    }

    @RequestMapping(method = RequestMethod.GET, value = "minibull.html")
    public String getMinibull(Model model){
        model.addAttribute("img_minibull", imageService.getRandomImageInTag(true, "minibull"));
        model.addAttribute("img_puppy", imageService.getRandomImageInTag(true, "minibull-puppy"));
        model.addAttribute("about_minibull", getHtml("about_minibull.html"));
        return "minibull";
    }

    @RequestMapping(method = RequestMethod.GET, value = "cadebou.html")
    public String getCadebou(Model model) {
        model.addAttribute("img_cadebou", imageService.getRandomImageInTag(true, "cadebou"));
        model.addAttribute("img_puppy", imageService.getRandomImageInTag(true, "cadebou-puppy"));
        model.addAttribute("about_cadebou", getHtml("about_cadebou.html"));
        return "cadebou";
    }

    @RequestMapping(method = RequestMethod.GET, value = "photoLarge.html")
    public String getPhotoLarge(@RequestParam(value = "tag") String tag,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "filterTag", required = false) String filterTag,
                                @RequestParam(value = "filterInClub", required = false) Boolean filterInClub,
                                Model model) {
        Image image = imageService.getByTagAndName(tag,name);
        List<Image> list = imageService.getPangeInTag(filterTag, filterInClub, 0, 10 * pageSize);
        Iterator<Image> it = list.iterator();
        while(it.hasNext()){
            Image i = it.next();
            if(i.getFilename().equals(image.getFilename()) && i.getTag().equals(image.getTag())){
                it.remove();
                break;
            }
        }
        list.add(0, image);
        model.addAttribute("images", list);
        return "photoLarge";
    }

    @RequestMapping(method = RequestMethod.GET, value = "photo.html")
    public String getPhoto(@RequestParam(value = "filter", required = false) String tag,
                           @RequestParam(value = "page", required = false) Integer page,
                           @RequestParam(value = "club", required = false) Boolean club,
                           Model model) {
        int totalCount = imageService.getTotalCountByTag(tag, club);
        int pageCount = (int)Math.floor((double)totalCount/pageSize);
        List<Page> list = new ArrayList<>(pageCount);
        if(pageCount > 1) {
            for (int i = 0; i < pageCount; i++) {
                list.add(new Page(i + 1, String.format("/photo.html?page=%d%s%s",
                        i + 1,
                        tag != null ? "&filter=" + tag : "",
                        club != null ? "&club=" + club : "")));
            }
        }
        if (page == null) {
            page = 1;
        }
        if(page > pageCount && pageCount > 0){
            page = pageCount;
        }
        model.addAttribute("club", club!= null ? club.toString() : "");
        model.addAttribute("filter", StringUtils.isEmpty(tag) ? null : tag);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", list);

        model.addAttribute("imagesPage", imageService.getPangeInTag(tag, club, pageSize * (page - 1), pageSize));

        return "photo";
    }

    @RequestMapping(method = RequestMethod.GET, value = "news.html")
    public String getNews(Model model) {
        model.addAttribute("articles", articleService.getAll());
        return "news";
    }

    @RequestMapping(method = RequestMethod.GET, value = "contacts.html")
    public String getContacts(Model model) {
        return "contacts";
    }

    @RequestMapping(method = RequestMethod.GET, value = "login.html")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           Model model) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET, value = "ajax/imageThumbs.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Image> getImagesThumbs(@RequestParam(value = "tag", required = false) String tag) {
        return imageService.getPangeInTag(tag, null, 0, 2 * pageSize);
    }


}
