package com.wd.zykt.service.Impl;

import com.wd.zykt.mapper.SeriesMapper;
import com.wd.zykt.pojo.Series;
import com.wd.zykt.service.SeriesService;
import com.wd.zykt.utils.Constant;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.ServiceResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

@Service
public class SeriesServiceImpl implements SeriesService {
    @Autowired
    private SeriesMapper seriesMapper;

    /**
     * 查询系列分类信息
     *
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Cacheable(cacheNames = Constant.RedisCode.SERIES_REDIS_SESSION)
    @Override
    public ServiceResult<LinkedList<Series>> QueAllSeries() {
        return ServiceResultHelper.genResultWithDataBaseSuccess(seriesMapper.selSeries());
    }
}
