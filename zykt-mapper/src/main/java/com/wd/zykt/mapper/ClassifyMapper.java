package com.wd.zykt.mapper;

import com.wd.zykt.pojo.Classify;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface ClassifyMapper {
    /**
     * 查询出当前类别下的分类科目
     * @return
     */
    public LinkedList<Classify> selClassifyByCateId(Integer id);

    /**
     * 常用分类筛选
     * @return
     */
    public LinkedList<Classify> selClassifyUse();
}
