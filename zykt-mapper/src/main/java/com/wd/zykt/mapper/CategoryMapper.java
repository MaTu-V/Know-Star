package com.wd.zykt.mapper;

import com.wd.zykt.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface CategoryMapper {


    /**
     * 查询当前系列课程的各个类别
     * @param id
     * @return
     */
    public LinkedList<Category> selCategoryBySeriesId(Integer id);

    /**
     * 查询全部类别
     * @return
     */
    public LinkedList<Category> selCategory();
}
