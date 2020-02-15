package com.wd.zykt.controller;

import com.wd.zykt.pojo.Classify;
import com.wd.zykt.service.ClassifyService;
import com.wd.zykt.utils.ControllerJSONResult;
import com.wd.zykt.utils.PagedUtil;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.WDJSONRESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;
    @Autowired
    private ControllerJSONResult result;

    @GetMapping("/getClassify/{id}")
    public ControllerJSONResult getClassify(@PathVariable("id") Integer id) {
        ServiceResult<LinkedList<Classify>> serviceResult = classifyService.QueClassifyByCateId(id);
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }

    @GetMapping("/getClassifyUse")
    public ControllerJSONResult getClassify() {
        ServiceResult<PagedUtil> serviceResult = classifyService.selClassifyUse(1);
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }
}
