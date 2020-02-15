package com.wd.zykt.utils;

import org.springframework.stereotype.Component;

/**
 * 返回到前台数据
 */
@Component
public class ControllerJSONResult {
    private Integer code;
    private String msg;
    private Object data;

    public ControllerJSONResult() {

    }

    public ControllerJSONResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ControllerJSONResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 服务器响应正常(自定义数据)
     *
     * @return
     */
    public ControllerJSONResult ok(Integer code, String msg) {
        return new ControllerJSONResult(code, msg);
    }

    /**
     * 服务器响应正常(自定义数据)
     *
     * @return
     */
    public ControllerJSONResult ok(Integer code, String msg, Object data) {
        return new ControllerJSONResult(code, msg, data);
    }

    /**
     * 服务器响应正常(默认编码)
     *
     * @return
     */
    public ControllerJSONResult ok() {
        return new ControllerJSONResult(Constant.SuccessCode.SERVER_SUCCESS_CODE, Constant.SuccessCode.SERVER_SUCCESS_MSG);
    }

    /**
     * 服务响应正常(默认编码)
     *
     * @param data 携带返回数据
     * @return
     */
    public ControllerJSONResult ok(Object data) {
        return new ControllerJSONResult(Constant.SuccessCode.SERVER_SUCCESS_CODE, Constant.SuccessCode.SERVER_SUCCESS_MSG, data);
    }

    /**
     * 服务响应失败(默认编码)
     *
     * @return
     */
    public ControllerJSONResult error() {
        return new ControllerJSONResult(Constant.ErrorCode.SERVER_ERROR_CODE, Constant.ErrorCode.SERVER_ERROR_MSG);
    }

    /**
     * 服务响应失败(默认编码)
     *
     * @param code 携带失败编码
     * @param msg  携带失败信息
     * @return
     */
    public ControllerJSONResult error(int code, String msg) {
        return new ControllerJSONResult(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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
        return "BlogJSONResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
