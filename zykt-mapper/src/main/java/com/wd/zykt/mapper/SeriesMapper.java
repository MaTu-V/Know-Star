package com.wd.zykt.mapper;

import com.wd.zykt.pojo.Series;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface SeriesMapper {
    /**
     * 查询系列分类信息
     * @return
     */
    public LinkedList<Series> selSeries();
}
