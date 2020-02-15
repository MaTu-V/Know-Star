package com.wd.zykt.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.wd.zykt.pojo.User;
import com.wd.zykt.service.UserService;
import com.wd.zykt.utils.ControllerJSONResult;
import com.wd.zykt.utils.ReadProteriesUtil;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.WDJSONRESULT;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/login")
public class LoginController extends BasicController {

    @Autowired
    private UserService userService;
    @Autowired
    private ControllerJSONResult result;
    @Autowired
    private ReadProteriesUtil readProteriesUtil;

    /**
     * 获取并返回用户openid（微信端）
     *
     * @param code
     * @return
     * @throws JSONException
     */
    @GetMapping("/getOpenid")
    public ControllerJSONResult getOpenid(@RequestParam(value = "code") String code,@RequestParam("type") Integer type) throws JSONException {
        //如果code为空则返回错误状态码
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(type)) {
            return result.error();
        }
        String httpUrl = null;
        if (type == 1){
            httpUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=" + readProteriesUtil.wxAppid + "&secret=" + readProteriesUtil.wxSecretKey + "&js_code=" + code + "&grant_type=authorization_code";
        }else if (type == 2){
            httpUrl = "https://api.q.qq.com/sns/jscode2session?appid=" + readProteriesUtil.qqAppid + "&secret=" + readProteriesUtil.qqSecretKey + "&js_code=" + code + "&grant_type=authorization_code";
        }else {
            return result.error();
        }
        String results = sendGetReq(httpUrl);// 发送http请求
        //System.out.println("results"+results);
        JSONObject res = new JSONObject(results);
        Map<String, Object> map = new HashMap<>();
        map.put("openid", res.get("openid"));
        map.put("session_key", res.get("session_key"));
        return result.ok(map);
    }


    @PostMapping("/register")
    public ControllerJSONResult register(@RequestBody User user) {

        // 如果user对象不存在则返回错误结果
        if (StringUtils.isEmpty(user) || StringUtils.isEmpty(user.getOpenId())) {
            return result.error();
        }

        // 判断是否存在该用户
        ServiceResult<User> userInfo = userService.QueUserByOpenId(user.getOpenId());

        if (userInfo.getData() == null) {
            // 如果不存在 插入数据
            String id = UUID.randomUUID().toString();
            user.setId(id);
            ServiceResult<Boolean> serviceResult = userService.InsUser(user);
            return serviceResult.isSuccess() ? result.ok(setUserRedisSessionToken(user)) : result.error(serviceResult.getCode(),result.getMsg());
        }
        //将当前用户加入redis
        return result.ok(setUserRedisSessionToken(userInfo.getData()));
    }

    /**
     * 请求session
     *
     * @param url
     * @return
     */
    private String sendGetReq(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            java.util.Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}
