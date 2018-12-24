package com.example.health.model;

public class ArticleCategory {
    String categoryName;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "categoryName='" + categoryName + '\'' +
                '}';
    }
}
