package com.wd.zykt.controller;

import com.wd.zykt.service.HotwordService;
import com.wd.zykt.utils.ControllerJSONResult;
import com.wd.zykt.utils.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/hot")
public class HotwordController {
    @Autowired
    private HotwordService hotwordService;
    @Autowired
    private ControllerJSONResult result;

    @PostMapping("/getHotword")
    public ControllerJSONResult getHotword() {
        ServiceResult<ArrayList<String>> serviceResult = hotwordService.QueHotword();
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }
}
