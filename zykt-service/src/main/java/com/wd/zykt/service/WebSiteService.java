package com.wd.zykt.service;

import com.wd.zykt.pojo.Website;
import com.wd.zykt.utils.ServiceResult;

public interface WebSiteService {
    /**
     * 查询网站信息
     * @return
     */
    public ServiceResult<Website> selWebSite();
}
