package com.example.health.controller.Artlice;

import com.example.health.mapper.ArticleCategoryMapper;
import com.example.health.mapper.ArticleContentMapper;
import com.example.health.mapper.ArticleMapper;
import com.example.health.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/article/category/{id}")
public class ArticleCategoryController {
    final static int SHOW_COUNT =1;
    final static int ARTICLE_ABOUT = 40;


    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    ArticleContentMapper articleContentMapper;

    @GetMapping("")
    public String getArticlehtml(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        List<Article>  lists=articleMapper.getArticleByCategoryId(id);
        model.addAttribute("lists",lists);
        model.addAttribute("categoryList",articleCategoryMapper.getAll());
        model.addAttribute("categoryName",articleCategoryMapper.getCategoryById(id).getCategoryName());
        List<Article> list = articleMapper.getArticleByCategoryId(id);
        String[] contentImageArray = new String[SHOW_COUNT];
        String[] articleIdArray = new String[SHOW_COUNT];

        int strip = 0;
        String targetID;
        while (strip < SHOW_COUNT) {

            targetID=list.get(1).getId();
            String content = articleContentMapper.getContentById(targetID);
            if (content.indexOf("src=\"") != -1) {
                contentImageArray[strip] = content.substring(
                        content.indexOf("src=\"") + 5, content.indexOf(".jpg\"") + 4
                );
                articleIdArray[strip] = targetID;
                strip++;
            } else {
            }
        }

        List<Article> articleList = new ArrayList<>();
        for (String Id: articleIdArray) {
            articleList.add(articleMapper.selectById(Id));

        }
        model.addAttribute("contentImageArray", contentImageArray);
        model.addAttribute("articleList", articleList);
        return "html/information";
    }

}