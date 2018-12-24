package com.example.health.mapper;

import com.example.health.model.Article;
import com.example.health.model.ArticleContent;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;



@Component
@Mapper
public interface ArticleContentMapper  {
    @Select("select content from article_content where id=#{id}")
    String getContentById(String id);


    @Update("update article_content set content=#{content} where id=#{id}")
    void updateContent(ArticleContent articleContent);

    @Insert("insert into article_content (content,id) value(#{content},#{id})")
    int insertContent(Article article);

    @Delete("delete from article_content where id=#{id}")
    void deleteByID(String id);


}
