package com.example.health.model;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.math.BigInteger;

public class ArticleContent {
    private String id;
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
