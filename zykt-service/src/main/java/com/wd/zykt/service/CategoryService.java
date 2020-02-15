package com.wd.zykt.service;


import com.wd.zykt.pojo.Category;
import com.wd.zykt.utils.ServiceResult;

import java.util.LinkedList;
import java.util.List;

public interface CategoryService {


    /**
     * 查询当前系列下的课程分类
     */
    public ServiceResult<LinkedList<Category>> QueCategoryBySeriesId(Integer id);
    /**
     * 查询全部课程分类
     */
    public ServiceResult<LinkedList<Category>> QueCategory();
}
