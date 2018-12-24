package com.example.health.controller.admin;

import com.example.health.bean.ArticleInfo;
import com.example.health.mapper.ArticleCategoryMapper;
import com.example.health.mapper.ArticleContentMapper;
import com.example.health.mapper.ArticleMapper;
import com.example.health.mapper.UserInfoMapper;
import com.example.health.model.Article;
import com.example.health.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin/article")
public class AdminArticleController {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    ArticleContentMapper articleContentMapper;

    @GetMapping("/show")
    public String getHtml( Model model){
//        model.addAttribute("a",articleMapper.selectPage(1,2)
//        .get(0));
        return "adminhtml/article";
    }

    @ResponseBody
    @GetMapping("/get")
    public  List<ArticleInfo> getArticle(int currentPage, int pageSize){
        List<Article> articleList=articleMapper.selectPage(currentPage-1,pageSize);
        List<ArticleInfo> articleInfoList=new ArrayList<>();
        for(Article article : articleList){
            String username=userInfoMapper.getUserInfoById(article.getCreatedBy()).getUsername();
            String category=articleCategoryMapper.getCategoryById(article.getCategoryId()).getCategoryName();
            articleInfoList.add(
                    new ArticleInfo(article,username,category)
            );
        }
        return articleInfoList;
    }

    @ResponseBody
    @GetMapping("/search")
    public ResponseData searchData(String value){
        List<Article> articleList=articleMapper.getArticleBySearchTitle("%"+value+"%");
        List<ArticleInfo> articleInfoList=new ArrayList<>();
        if(articleList.size()==0){
            return new ResponseData(false,"查询内容为空",null);
        }
        for(Article article : articleList){
            String username=userInfoMapper.getUserInfoById(article.getCreatedBy()).getUsername();
            String category=articleCategoryMapper.getCategoryById(article.getCategoryId()).getCategoryName();
            articleInfoList.add(
                    new ArticleInfo(article,username,category)
            );
        }
        return new ResponseData(true,"查询成功",articleInfoList);

    }

    @ResponseBody
    @PostMapping("/delete")
    public ResponseData deleteArticle(@RequestBody String id){
         if(1==articleMapper.deleteByID(id)){
             return new ResponseData(true,"删除成功",null);
         }else {
             return new ResponseData(false,"删除失败",null);
         }
    }

    @GetMapping("/{articleid}")
    public String getHtml( @PathVariable("articleid") String articleId,
                          ModelMap modelMap){
        Article article=articleMapper.selectById(articleId);
        String articleContent = articleContentMapper.getContentById(articleId);
        modelMap.addAttribute("article",article);
        modelMap.addAttribute("articleContent",articleContent);
        return "adminhtml/article_show";
    }

}
