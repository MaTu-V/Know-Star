package com.wd.zykt.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wd.zykt.mapper.ClassifyMapper;
import com.wd.zykt.pojo.Classify;
import com.wd.zykt.service.ClassifyService;
import com.wd.zykt.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.LinkedList;

@Service
public class ClassifyServiceImpl implements ClassifyService {
    @Autowired
    ClassifyMapper classifyMapper;


    /**
     * 查询出当前类别下的分类科目
     *
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Cacheable(cacheNames = Constant.RedisCode.CLASSIFY_REDIS_SESSION, key = "#a0")
    @Override
    public ServiceResult<LinkedList<Classify>> QueClassifyByCateId(Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }

        return ServiceResultHelper.genResultWithDataBaseSuccess(classifyMapper.selClassifyByCateId(id));
    }

    /**
     * 常用分类筛选
     *
     * @return
     */
    @Override
    public ServiceResult<PagedUtil> selClassifyUse(int page) {
        if (StringUtils.isEmpty(page)){
            page = 1;
        }
        // 设置查询的分页条件
        Page<Object> pageData = PageHelper.startPage(page, Constant.PageHelper.PAGE_SIZE);
        LinkedList<Classify> classifies = classifyMapper.selClassifyUse();
        return ServiceResultHelper.genResultWithDataBaseSuccess(PagedUtilHelper.genResultPagedData(classifies, pageData));
    }
}
