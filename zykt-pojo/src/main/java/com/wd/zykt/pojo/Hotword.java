package com.wd.zykt.pojo;

public class Hotword {
    private Integer id;
    private String hotword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotword() {
        return hotword;
    }

    public void setHotword(String hotword) {
        this.hotword = hotword;
    }

    @Override
    public String toString() {
        return "Hotword{" +
                "id=" + id +
                ", hotword='" + hotword + '\'' +
                '}';
    }
}
