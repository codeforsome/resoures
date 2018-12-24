package com.example.health.controller;

import com.example.health.mapper.ArticleCategoryMapper;
import com.example.health.mapper.ArticleMapper;
import com.example.health.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/user/{id}/myinfo"})
public class MyInfoController {
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    ArticleMapper articleMapper;

    @GetMapping("")
    String getHtml(@PathVariable("id") BigInteger id ,ModelMap modelMap, HttpServletRequest request){
        modelMap.addAttribute("categoryList",articleCategoryMapper.getAll());
        BigInteger loginID = (BigInteger)request.getSession().getAttribute("userId");
        if (null != loginID ) {
            modelMap.addAttribute("isLogin",true);
            modelMap.addAttribute("id",loginID.toString());
        }
        List<Article> articleList= articleMapper.selectAllByAuthor(id);
        modelMap.addAttribute("articleList",articleList);
        return "html/myinfo";
    }
}
