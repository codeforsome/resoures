package com.example.health.mapper;

import com.example.health.model.Article;
import com.example.health.model.ArticleCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ArticleCategoryMapper {
    @Select("select * from article_category")
    List<ArticleCategory> getAll();

    @Update("update article_category set category_name=#{categoryName}"+
            "where id=#{id}")
    void updateNoe(ArticleCategory articleCategory);

    @Select("select * from article_category where category_name=#{categoryName}")
    ArticleCategory selectByName(ArticleCategory articleCategory);

    @Select("select * from article_category where id=#{id}")
    ArticleCategory selectCategoryExistedById(int id);

    @Select("select * from article_category where category_name=#{categoryName} and id!=#{id}")
    ArticleCategory isExistedCategoryName(ArticleCategory articleCategory);

    @Insert("insert into article_category(category_name)" +
            "value(#{categoryName})")
    void insertByName(ArticleCategory articleCategory);

    @Delete("delete  from article_category where id=#{id}")
    void deleteById(int id);

    @Select("select * from article_category where id=#{id}")
    ArticleCategory getCategoryById(int id);


}
