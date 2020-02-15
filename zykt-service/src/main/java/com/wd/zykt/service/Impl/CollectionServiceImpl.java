package com.wd.zykt.service.Impl;

import com.wd.zykt.mapper.CollectionMapper;
import com.wd.zykt.pojo.Collections;
import com.wd.zykt.service.CollectionService;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.ServiceResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionMapper collectionMapper;

    /**
     * 查询是否收藏
     *
     * @param collections
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ServiceResult<Boolean> isExistCollection(Collections collections) {
        if (StringUtils.isEmpty(collections) || StringUtils.isEmpty(collections.getBookId())) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        if (!StringUtils.isEmpty(collectionMapper.selCollection(collections))) {
            return ServiceResultHelper.genResult(1001, "未收藏", true);
        } else {
            return ServiceResultHelper.genResult(-1001, "已收藏", false);
        }

    }

    /**
     * 用户点击收藏
     *
     * @param collection
     * @param status
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServiceResult<Boolean> chooseCollection(Collections collection, boolean status) {

        if (StringUtils.isEmpty(collection) || StringUtils.isEmpty(collection.getUserId())) {
            return ServiceResultHelper.genResultWithParamFailed();
        }

        if (status) {
            //设置ID
            collection.setId(UUID.randomUUID().toString());
            System.out.println(collection);
            if (collectionMapper.insCollection(collection) > 0) {
                return ServiceResultHelper.genResultWithDataBaseSuccess();
            }
        } else {
            if (collectionMapper.delCollection(collection) > 0) {
                return ServiceResultHelper.genResultWithDataBaseSuccess();
            }
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }
}
