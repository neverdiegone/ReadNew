package com.dinhnguyen.readnew;

/**
 * Created by Administrator on 3/15/2017.
 */

public class New {
    private int id;
    private String title;
    private String titlesummary;
    private String image;
    private String content;
    private String writingmaster;

    public New() {
    }

    public New(int id, String title, String titlesummary, String image, String content, String writingmaster) {
        this.id = id;
        this.title = title;
        this.titlesummary = titlesummary;
        this.image = image;
        this.content = content;
        this.writingmaster = writingmaster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitlesummary() {
        return titlesummary;
    }

    public void setTitlesummary(String titlesummary) {
        this.titlesummary = titlesummary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWritingmaster() {
        return writingmaster;
    }

    public void setWritingmaster(String writingmaster) {
        this.writingmaster = writingmaster;
    }
}
