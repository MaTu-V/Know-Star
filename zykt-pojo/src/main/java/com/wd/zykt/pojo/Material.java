package com.wd.zykt.pojo;

import java.sql.Timestamp;

public class Material {
    private String id;
    private String name;
    private String fileUrl;
    private Integer types;
    private Timestamp insertTime;
    private String userId;
    private String bookId;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }


    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }


    @Override
    public String toString() {
        return "Material{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                ", types=" + types +
                ", insertTime=" + insertTime +
                ", userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                '}';
    }
}
