package com.wd.zykt.service;

import com.wd.zykt.pojo.Material;
import com.wd.zykt.utils.ServiceResult;

import java.util.LinkedList;

public interface MaterialService {
    /**
     * 查找书籍对应资料
     *
     * @param id
     * @param type
     * @return
     */
    public ServiceResult<LinkedList<Material>> QueMaterialsByBookId(String id,String type);

    /**
     * 插入相关资料
     *
     * @param material
     * @return
     */
    public ServiceResult<Boolean> insMaterial(Material material);

    /**
     * 获取当前目录下图片
     * @param page
     * @param url
     * @return
     */
    public ServiceResult<String[]> getMaterialImg(Integer page, String url);
}
