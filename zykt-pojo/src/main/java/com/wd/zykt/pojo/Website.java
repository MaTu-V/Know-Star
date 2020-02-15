package com.wd.zykt.pojo;

import java.io.Serializable;

public class Website{
    private Integer id;
    private String title;
    private String keyword;
    private String describe;
    private String webBack;
    private String appBack;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getWebBack() {
        return webBack;
    }

    public void setWebBack(String webBack) {
        this.webBack = webBack;
    }

    public String getAppBack() {
        return appBack;
    }

    public void setAppBack(String appBack) {
        this.appBack = appBack;
    }

    @Override
    public String toString() {
        return "Website{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", keyword='" + keyword + '\'' +
                ", describe='" + describe + '\'' +
                ", webBack='" + webBack + '\'' +
                ", appBack='" + appBack + '\'' +
                '}';
    }
}
