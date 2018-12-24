package com.example.health.controller;

import com.example.health.mapper.ArticleCategoryMapper;
import com.example.health.mapper.ArticleContentMapper;
import com.example.health.mapper.ArticleMapper;
import com.example.health.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
    final static int SHOW_COUNT=3;
    final static int ARTICLE_ABOUT=40;

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    ArticleContentMapper articleContentMapper;

    @RequestMapping("*")
    public String getIndex(ModelMap modelMap, HttpServletRequest request){
        modelMap.addAttribute("categoryList",articleCategoryMapper.getAll());
        BigInteger loginID = (BigInteger)request.getSession().getAttribute("userId");
        if (null != loginID ) {
            modelMap.addAttribute("isLogin",true);
            modelMap.addAttribute("id",loginID.toString());
        }

        String []contentImageArray=new String[SHOW_COUNT];
        String []articleIdArray=new String[SHOW_COUNT];
        int strip=0;
        int startIndex=0;
        String targetID;
        while (strip<SHOW_COUNT){
            Article article= articleMapper.getcontent(startIndex,1);
            targetID=article.getId();
            String content=articleContentMapper.getContentById(targetID);
            if( content.indexOf("src=\"") !=-1){
                contentImageArray[strip]=content.substring(
                        content.indexOf("src=\"")+5,content.indexOf(".jpg\"")+4
                );
                articleIdArray[strip]=targetID;
                strip++;
            }else {
            }
            startIndex++;
        }
        List<Article> articleList=new ArrayList<>();
        for(String id:articleIdArray){
            articleList.add(articleMapper.selectById(id));

        }
        modelMap.addAttribute("contentImageArray",contentImageArray);
        modelMap.addAttribute("articleList",articleList);
        modelMap.addAttribute("articleListNew",
                articleMapper.selectPage(0,6));
        return "html/index";
    }

}
