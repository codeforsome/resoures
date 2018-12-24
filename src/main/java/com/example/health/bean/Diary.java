package com.example.health.bean;

import java.util.Date;


public class Diary {
    public Diary() {
        super();
        // TODO Auto-generated constructor stub
    }

    private Integer diaryId;
    private String title;
    private String content;
    private Date releaseDate;
    private Integer typeId = -1;

    public Diary(String title, String content, int typeId) {
        super();
        this.title = title;
        this.content = content;
        this.typeId = typeId;
    }

    public Integer getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(Integer diaryId) {
        this.diaryId = diaryId;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

}
