package com.example.health.controller.Artlice;

import com.example.health.mapper.ArticleCategoryMapper;
import com.example.health.mapper.ArticleContentMapper;
import com.example.health.mapper.ArticleMapper;
import com.example.health.mapper.UserInfoMapper;
import com.example.health.model.Article;
import com.example.health.model.ArticleContent;
import com.example.health.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.UUID;


@Controller
@RequestMapping(value = {"/user/{id}"})
public class ArticleController {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleContentMapper articleContentMapper;

    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @GetMapping("/article/{articleid}")
    public String getHtml(@PathVariable("id") String id ,@PathVariable("articleid") String articleId,
                          ModelMap modelMap, HttpServletRequest request){
      Article article=articleMapper.selectById(articleId);
        String articleContent = articleContentMapper.getContentById(articleId);
        modelMap.addAttribute("article",article);
        modelMap.addAttribute("articleContent",articleContent);

        return "html/article";
    }

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

    @GetMapping("/edit/{item.id}")
    public String updateHtml(@PathVariable("id") BigInteger id,@PathVariable("item.id") String articleId,ModelMap modelMap, HttpServletRequest request){
        Article article=articleMapper.selectById(articleId);
        modelMap.addAttribute("id",id);

        String articleContent = articleContentMapper.getContentById(articleId);
        if(userInfoMapper.getUserRoleById(id).equals("医者")){
            modelMap.addAttribute("doctor",true);
            modelMap.addAttribute("categoryList",articleCategoryMapper.getAll());
        }else {
            modelMap.addAttribute("doctor",false);
        }
        if(id!=null&&articleId.length()!=0){
            modelMap.addAttribute("isregist",true);
        }else {
            modelMap.addAttribute("isregist",false);
        }
        modelMap.addAttribute("article",article);
        modelMap.addAttribute("articleContent",articleContent);
        return "html/edit_article";
    }

    @ResponseBody
    @PostMapping("/edit/{article_id}/updata")
    public ResponseData update(@PathVariable("article_id") String  articleId,@RequestBody  Article article){
        article.setId(articleId);

        articleMapper.update(article);
        ArticleContent articleContent=new ArticleContent();
        articleContent.setContent(article.getContent());
        articleContent.setId(article.getId());
        articleContentMapper.updateContent(articleContent);

        return new ResponseData(true,"修改成功",null);
    }


    @ResponseBody
    @GetMapping ("/delete/{item.id}")
        public ResponseData delete(@PathVariable("item.id") String articleid){
            articleMapper.deleteByID(articleid);
//            articleContentMapper.deleteByID(articleid);
            return new ResponseData(true,"删除成功",null);
        }


    @ResponseBody
    @PostMapping("/edit/add")
    public ResponseData get(@RequestBody Article article) {
        String UUIDid=UUID.randomUUID().toString().replace("-","");
        article.setId(UUIDid);
        articleMapper.insertNoe(article);
        articleContentMapper.insertContent(article);
        return new ResponseData(true,"提交成功",null);
    }
}
