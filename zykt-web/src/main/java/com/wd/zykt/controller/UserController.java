package com.wd.zykt.controller;

import com.wd.zykt.pojo.User;
import com.wd.zykt.pojo.VO.UserVO;
import com.wd.zykt.service.BookService;
import com.wd.zykt.service.UserService;
import com.wd.zykt.utils.ControllerJSONResult;
import com.wd.zykt.utils.PagedUtil;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.WDJSONRESULT;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    private UserService userService;
    @Autowired
    private ControllerJSONResult result;

    @PostMapping("/getUserInfo")
    public ControllerJSONResult getUserInfo(String id) {
        // 传入id 获取个人信息
        ServiceResult<User> serviceResult = userService.QueUserById(id);
        return serviceResult.isSuccess() ? result.ok(setUserRedisSessionToken(serviceResult.getData())) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }

    @PostMapping("/updUserQian")
    public ControllerJSONResult updUserQian(String id) {
        // 传入id 获取个人信息
        ServiceResult<Boolean> serviceResult = userService.updUseById(id);
        return serviceResult.isSuccess() ? result.ok() : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }
}
