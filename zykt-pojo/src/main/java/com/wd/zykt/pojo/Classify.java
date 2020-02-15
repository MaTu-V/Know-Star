package com.wd.zykt.pojo;

public class Classify {
    private Integer id;
    private String name;
    private String icon;
    private String iconBack;
    private Integer categoryId;
    private Integer status;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconBack() {
        return iconBack;
    }

    public void setIconBack(String iconBack) {
        this.iconBack = iconBack;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", iconBack='" + iconBack + '\'' +
                ", categoryId=" + categoryId +
                ", status=" + status +
                '}';
    }
}
