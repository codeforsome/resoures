package com.example.health.model;


import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class Article {
    private String id;
    private String title;
    private String content;
    private BigInteger createdBy;
    private Timestamp createdAt;
    private String contentAbout;
    private int   categoryId;

    public String getContentAbout() {
        return contentAbout;
    }

    public void setContentAbout(String contentAbout) {
        this.contentAbout = contentAbout;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigInteger getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigInteger createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdBy=" + createdBy +
                ", createdAt=" + createdAt +
                ", contentAbout='" + contentAbout + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
