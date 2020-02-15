package com.wd.zykt.mapper;

import com.wd.zykt.pojo.Collections;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionMapper {
    /**
     * 查询是否收藏
     * @param collections
     * @return
     */
    public Collections selCollection(Collections collections);
    /**
     * 用户添加收藏
     * @param collection
     * @return
     */
    public int insCollection(Collections collection);

    /**
     * 用户取消收藏
     * @param collection
     * @return
     */
    public int delCollection(Collections collection);
}
