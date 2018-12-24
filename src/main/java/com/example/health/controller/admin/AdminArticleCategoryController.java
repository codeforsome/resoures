package com.example.health.controller.admin;

import com.example.health.mapper.ArticleCategoryMapper;
import com.example.health.model.ArticleCategory;
import com.example.health.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin/article")
public class AdminArticleCategoryController {
    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @GetMapping("/category")
    public String  get(){
        return "adminhtml/article_category";
    }

    @ResponseBody
    @RequestMapping("/category/update")
    public ResponseData update(@RequestBody ArticleCategory articleCategory){
       if(articleCategoryMapper.selectCategoryExistedById(articleCategory.getId())==null){
           return new ResponseData(false,"修改的名称不存在",null);
       }else if(articleCategoryMapper.isExistedCategoryName(articleCategory)!=null){
           return new ResponseData(false,"该名称已经存在了",null);
       }else {
           articleCategoryMapper.updateNoe(articleCategory);
           return new ResponseData(true,"修改成功",null);
       }
    }

    @ResponseBody
    @RequestMapping("/category/delete")
    public ResponseData  delete(@RequestBody int id){
        System.out.print(id);
        if(articleCategoryMapper.selectCategoryExistedById(id)==null){
            return new ResponseData(false,"该名称不存在",null);
        }else {
            articleCategoryMapper.deleteById(id);
            return new ResponseData(true,"删除成功",null);
        }
    }

    @ResponseBody
    @PostMapping("/category/insert")
    public ResponseData  insertCategory(@RequestBody ArticleCategory articleCategory){
        if(articleCategory==null){
            return new ResponseData(false,"提交为空",null);
        }else if(articleCategoryMapper.selectByName(articleCategory)!=null){
            return new ResponseData(false,"该名称已经存在",null);
        }else {
            articleCategoryMapper.insertByName(articleCategory);
            return new ResponseData(true,"添加成功",null);
        }
    }

    @ResponseBody
    @GetMapping("/category/data")
    public List<ArticleCategory> getCategory(){
      return articleCategoryMapper.getAll();
    }
}
