package com.wd.zykt.mapper;

import com.wd.zykt.pojo.Material;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface MaterialMapper {
    /**
     * 查找书籍对应资料
     *
     * @param id
     * @param types
     * @return
     */
    public LinkedList<Material> selMaterialByBookId(String id, String types);

    /**
     * 插入相关资料
     *
     * @param material
     * @return
     */
    public int insMaterial(Material material);
}
