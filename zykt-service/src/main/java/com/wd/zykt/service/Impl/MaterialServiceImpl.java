package com.wd.zykt.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wd.zykt.mapper.MaterialMapper;
import com.wd.zykt.pojo.Material;
import com.wd.zykt.service.MaterialService;
import com.wd.zykt.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.UUID;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Autowired
    private ReadProteriesUtil readProteriesUtil;

    /**
     * 查询对应书籍资料
     * @param id
     * @param type
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ServiceResult<LinkedList<Material>> QueMaterialsByBookId(String id,String type) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        return ServiceResultHelper.genResultWithDataBaseSuccess(materialMapper.selMaterialByBookId(id,type));
    }

    /**
     * 插入相关资料
     *
     * @param material
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ServiceResult<Boolean> insMaterial(Material material) {
        if (StringUtils.isEmpty(material.getUserId()) || StringUtils.isEmpty(material.getBookId())) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        // 获取当前服务器文件路径
        String filePath = readProteriesUtil.FILE_SPACE + material.getFileUrl();

        String materialId = UUID.randomUUID().toString();
        // 将服务器pdf文件转化为图片，图片路径
        String ImageDir = readProteriesUtil.FILE_SPACE + "/zykt_material_image/" + material.getUserId() + '/' + materialId + '/';
        String finalPath = PDFUtil.pdfToImagePath(filePath,ImageDir);
        material.setId(materialId);
        material.setFileUrl(finalPath);
        if (materialMapper.insMaterial(material) > 0) {
            return ServiceResultHelper.genResultWithDataBaseSuccess();
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }

    /**
     * 获取当前目录下图片
     *
     * @param url
     * @return
     */
    @Override
    public ServiceResult<String[]> getMaterialImg(Integer page, String url) {
        File file = new File(url);
        // 设置查询的分页条件
        Page<Object> pageData = PageHelper.startPage(page,Constant.PageHelper.PAGE_SIZE);
        String[] fileName = file.list();
        //收集数据
        return ServiceResultHelper.genResultWithDataBaseSuccess(fileName);
}
}
