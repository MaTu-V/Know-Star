package com.wd.zykt.service.Impl;

import com.wd.zykt.mapper.WebsiteMapper;
import com.wd.zykt.pojo.Website;
import com.wd.zykt.service.WebSiteService;
import com.wd.zykt.utils.Constant;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.ServiceResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WebSiteServiceImpl implements WebSiteService {
    @Autowired
    private WebsiteMapper websiteMapper;

    /**
     * 查询网站信息
     *
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Cacheable(cacheNames = Constant.RedisCode.WEBSITE_REDIS_SESSION)
    @Override
    public ServiceResult<Website> selWebSite() {
        return ServiceResultHelper.genResultWithDataBaseSuccess(websiteMapper.selWebsite());
    }
}
