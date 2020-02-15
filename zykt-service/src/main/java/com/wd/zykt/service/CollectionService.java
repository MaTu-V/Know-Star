package com.wd.zykt.service;

import com.wd.zykt.pojo.Collections;
import com.wd.zykt.utils.ServiceResult;

public interface CollectionService {

    /**
     * 查询是否收藏
     *
     * @param collections
     * @return
     */
    public ServiceResult<Boolean> isExistCollection(Collections collections);

    /**
     * 用户收藏
     *
     * @param collection
     * @param status
     * @return
     */
    public ServiceResult<Boolean> chooseCollection(Collections collection, boolean status);
}
