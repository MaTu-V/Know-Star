package com.wd.zykt.service;

import com.wd.zykt.pojo.Classify;
import com.wd.zykt.utils.PagedUtil;
import com.wd.zykt.utils.ServiceResult;

import java.util.LinkedList;
import java.util.List;

public interface ClassifyService {
    /**
     * 查询出当前类别下的分类科目
     *
     * @param id
     * @return
     */
    public ServiceResult<LinkedList<Classify>> QueClassifyByCateId(Integer id);

    /**
     * 常用分类筛选
     * @return
     */
    public ServiceResult<PagedUtil> selClassifyUse(int page);
}
