package com.example.health.controller;

import com.example.health.mapper.ArticleCategoryMapper;
import com.example.health.mapper.ArticleContentMapper;
import com.example.health.mapper.ArticleMapper;
import com.example.health.mapper.UserInfoMapper;
import com.example.health.model.Article;
import com.example.health.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.UUID;


@Controller
@RequestMapping(value = {"/user/{id}"})
public class EditArticleController {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleContentMapper articleContentMapper;

    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @GetMapping("/edit")
    public String getEdit(@PathVariable("id") BigInteger id, ModelMap modelMap){
        modelMap.addAttribute("id",id);
        if(userInfoMapper.getUserRoleById(id).equals("医者")){
            modelMap.addAttribute("doctor",true);
            modelMap.addAttribute("categoryList",articleCategoryMapper.getAll());

        }else {
            modelMap.addAttribute("doctor",false);
        }
        return "html/edit_article";
    }
    @ResponseBody
    @PostMapping("/edit/add")
    public String get(@RequestBody Article article) {
        String UUIDid=UUID.randomUUID().toString().replace("-","");
        article.setId(UUIDid);
        articleContentMapper.insertContent(article);
        articleMapper.insertNoe(article);
        return null;
    }
}
