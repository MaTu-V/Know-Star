package com.wd.zykt.controller;

import com.wd.zykt.pojo.Series;
import com.wd.zykt.service.SeriesService;
import com.wd.zykt.utils.ControllerJSONResult;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.WDJSONRESULT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;
    @Autowired
    private ControllerJSONResult result;

    @GetMapping("/getSeries")
    public ControllerJSONResult getSeries(){
        ServiceResult<LinkedList<Series>> serviceResult = seriesService.QueAllSeries();
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()):result.error(serviceResult.getCode(),serviceResult.getMsg());
    }

}
