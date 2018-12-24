package com.example.health.controller;

import com.example.health.mapper.ArticleContentMapper;
import com.example.health.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping(value ={"/user/article/{id}/"} )
public class ArticleInfoController {
    @Autowired
    ArticleContentMapper articleContentMapper;
    @GetMapping("")
    public String getHtml(@PathVariable("id") String id , ModelMap modelMap, HttpServletRequest request){

       String articleContent = articleContentMapper.getContentById(id);
        modelMap.addAttribute("articleContent",articleContent);

        return "html/articleinfo";
    }
}
