package com.wd.zykt.service;

import com.wd.zykt.pojo.Series;
import com.wd.zykt.utils.ServiceResult;

import java.util.LinkedList;

public interface SeriesService {
    /**
     * 查询系列分类信息
     * @return
     */
    public ServiceResult<LinkedList<Series>> QueAllSeries();
}
