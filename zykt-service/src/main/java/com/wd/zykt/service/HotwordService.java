package com.wd.zykt.service;


import com.wd.zykt.utils.ServiceResult;


import java.util.ArrayList;

public interface HotwordService {

    /**
     * 查询热搜词
     */
    public ServiceResult<ArrayList<String>> QueHotword();
}
