package com.example.health.mapper;

import com.example.health.model.Article;
import com.example.health.model.ArticleCategory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

@Component
@Mapper
public interface ArticleMapper {

    @Insert("insert into article (title,content_about,created_by,category_id,id) " +
            "value(#{title},#{contentAbout},#{createdBy},#{categoryId},#{id})")
    int insertNoe(Article article);

    @Update("update article set title=#{title},content_about=#{contentAbout},category_id=#{categoryId}" +
            " where id=#{id}")
    void update(Article article);

    @Update("update article set title=#{title}"+
            "where id=#{id}")
    void updateNoe(ArticleCategory articleCategory);

    @Select("select * from article  limit #{nums},#{row}")
    Article getcontent(@Param("nums")int nums, @Param("row")int row);

    @Select("select * from article  where created_by=#{id} order by created_at DESC ")
    List<Article> selectAllByAuthor(BigInteger id);

    @Select("select * from article  where id=#{id} ")
    Article selectById(String  id);

    @Delete("delete from article where id=#{id}")
    int deleteByID(String id);

    @Select("select * from article limit #{offset},#{rows}")
    List<Article> selectPage(@Param("offset")int offset, @Param("rows")int rows);

    @Select("select * from article where category_id=#{categoryId}")
    List<Article> getArticleByCategoryId(int categoryId);

    @Select("select * from article where title like #{target}")
    List<Article> getArticleBySearchTitle(String target);
}
