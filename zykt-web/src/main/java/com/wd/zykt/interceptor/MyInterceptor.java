package com.wd.zykt.interceptor;

import com.wd.zykt.utils.Constant;
import com.wd.zykt.utils.RedisUtil;
import com.wd.zykt.utils.WDJSONRESULT;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("user-id");
        String userToken = request.getHeader("user-token");
        //有一个不存在则拦截
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(userToken)) {
            returnJson(response, new WDJSONRESULT().errorToken("访问失败", null));
            return false;
        }
        //获取缓存中对应用户信息
        String uniqueToken = (String) redisUtil.get(Constant.RedisCode.USER_REDIS_SESSION + ":" + userId);
        //查询当前缓存中的token不存在或者与之不匹配
        if (StringUtils.isEmpty(uniqueToken) || !userToken.equals(uniqueToken)) {
            returnJson(response, new WDJSONRESULT().errorToken("访问失败", null));
            return false;
        }
        return true;

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public void returnJson(HttpServletResponse response, WDJSONRESULT wdjsonresult) throws IOException, JSONException {
        OutputStream out = null;
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/json");
        try {
            out = response.getOutputStream();
            JSONObject res = new JSONObject();
            // TODO: 优化
            res.put("status",wdjsonresult.getStatus());
            res.put("msg",wdjsonresult.getMsg());
            res.put("data",wdjsonresult.getData());
            out.write(res.toString().getBytes("utf-8"));
            out.flush();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
