package com.wd.zykt.service.Impl;

import com.wd.zykt.mapper.HotwordMapper;
import com.wd.zykt.service.HotwordService;
import com.wd.zykt.utils.Constant;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.ServiceResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotwordServiceImpl implements HotwordService {
    @Autowired
    HotwordMapper hotwordMapper;

    /**
     * 查询热搜词
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @CachePut(cacheNames = Constant.RedisCode.HOTWORD_REDIS_SESSION)
    @Override
    public ServiceResult<ArrayList<String>> QueHotword() {
        return ServiceResultHelper.genResultWithDataBaseSuccess(hotwordMapper.selHotWord());
    }
}
