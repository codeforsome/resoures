package com.example.health.bean;

import com.example.health.model.Article;

public class ArticleInfo {
    Article article;
    String createdBy;
    String category;

    public ArticleInfo(Article article, String createdBy, String category) {
        this.article = article;
        this.createdBy = createdBy;
        this.category = category;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
