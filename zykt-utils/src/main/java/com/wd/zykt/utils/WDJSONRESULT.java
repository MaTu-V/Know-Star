package com.wd.zykt.utils;


import org.springframework.stereotype.Component;

@Component
public class WDJSONRESULT {
    //状态码
    private Integer status;
    //响应信息
    private String msg;
    //响应数据
    private Object data;

    public WDJSONRESULT() {
    }

    public WDJSONRESULT(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public WDJSONRESULT ok(String msg, Object data)
    {
        return new WDJSONRESULT(200, msg, data);
    }

    public WDJSONRESULT error(String msg, Object data) {
        return new WDJSONRESULT(500, msg, data);
    }
    public WDJSONRESULT errorToken(String msg, Object data) {
        return new WDJSONRESULT(502, msg, data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WDJSONRESULT{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
