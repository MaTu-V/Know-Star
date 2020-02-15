package com.wd.zykt.controller;

import com.wd.zykt.pojo.Series;
import com.wd.zykt.pojo.Website;
import com.wd.zykt.service.WebSiteService;
import com.wd.zykt.utils.ControllerJSONResult;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.WDJSONRESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/website")
public class WebSiteController {
    @Autowired
    private WebSiteService webSiteService;
    @Autowired
    private ControllerJSONResult result;
    @GetMapping("/getWebsite")
    public ControllerJSONResult getWebsite() {
        ServiceResult<Website> serviceResult = webSiteService.selWebSite();
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()):result.error(serviceResult.getCode(),serviceResult.getMsg());
    }

}
