package com.wd.zykt.service.Impl;

import com.wd.zykt.mapper.CategoryMapper;
import com.wd.zykt.pojo.Category;
import com.wd.zykt.service.CategoryService;
import com.wd.zykt.utils.Constant;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.ServiceResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.LinkedList;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 查询全部课程分类
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Cacheable(cacheNames = Constant.RedisCode.CATEGORY_ALL_REDIS_SESSION)
    @Override
    public ServiceResult<LinkedList<Category>> QueCategory() {
        return ServiceResultHelper.genResultWithDataBaseSuccess(categoryMapper.selCategory());
    }

    /**
     * 查询当前系列课程的各个类别
     *
     * @param id
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Cacheable(cacheNames = Constant.RedisCode.CATEGORY_REDIS_SESSION, key = "#a0")
    @Override
    public ServiceResult<LinkedList<Category>> QueCategoryBySeriesId(Integer id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }

        return ServiceResultHelper.genResultWithDataBaseSuccess(categoryMapper.selCategoryBySeriesId(id));
    }
}
