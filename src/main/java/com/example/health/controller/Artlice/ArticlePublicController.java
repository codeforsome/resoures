package com.example.health.controller.Artlice;

import com.example.health.mapper.ArticleContentMapper;
import com.example.health.mapper.ArticleMapper;
import com.example.health.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/article/{id}"})
public class ArticlePublicController {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleContentMapper articleContentMapper;

    @GetMapping("")
     public String  getHtml(@PathVariable("id") String id , Model model){
        Article article=articleMapper.selectById(id);
        String articleContent = articleContentMapper.getContentById(id);
        model.addAttribute("article",article);
        model.addAttribute("articleContent",articleContent);
        return "html/article";
    }



}
