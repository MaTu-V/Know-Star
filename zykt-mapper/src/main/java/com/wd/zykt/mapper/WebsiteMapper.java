package com.wd.zykt.mapper;

import com.wd.zykt.pojo.Website;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsiteMapper {
    /**
     * 查找网站信息
     * @return
     */
    public Website selWebsite();
}
