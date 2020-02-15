package com.wd.zykt.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.wd.zykt.pojo.Collections;
import com.wd.zykt.service.CollectionService;
import com.wd.zykt.utils.ControllerJSONResult;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.WDJSONRESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @Autowired
    ControllerJSONResult result;

    @PostMapping("/addCollection")
    public ControllerJSONResult addCollection(@RequestBody Collections collection, @RequestParam(value = "status", required = true) boolean status) {

        ServiceResult<Boolean> serviceResult = collectionService.chooseCollection(collection, status);
        return serviceResult.isSuccess() ? result.ok() : result.error(serviceResult.getCode(),serviceResult.getMsg());
    }

    @PostMapping("/isCollection")
    public ControllerJSONResult isCollection(@RequestBody Collections collection) {
        ServiceResult<Boolean> serviceResult = collectionService.isExistCollection(collection);
        return result.ok(serviceResult.isSuccess());
    }
}
