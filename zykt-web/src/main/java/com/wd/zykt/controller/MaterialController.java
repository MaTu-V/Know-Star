package com.wd.zykt.controller;

import com.wd.zykt.pojo.Material;
import com.wd.zykt.service.MaterialService;
import com.wd.zykt.utils.*;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.Map;

@RestController
@RequestMapping("/material")
public class MaterialController extends BasicController {

    @Autowired
    private MaterialService materialsService;

    @Autowired
    private ControllerJSONResult result;

    @PostMapping("/getMaterial")
    public ControllerJSONResult getMaterials(@RequestParam("id") String id,@RequestParam("types") String types) {
        ServiceResult<LinkedList<Material>> serviceResult = materialsService.QueMaterialsByBookId(id,types);
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }


    /**
     * 用户上传资源文件
     *
     * @return
     */
    @PostMapping("/uploadFile")
    public ControllerJSONResult uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        //获取上传者的id
        if (StringUtils.isEmpty(userId)) {
            return result.error();
        }
        //定义上传书籍的文件命名空间
        String fileSpace = "/zykt_materials";
        // 保存到文件的相对路径
        String uploadPathDB = "/" + userId + "/Files";
        // 上传文件资源
        return uploadResources(file, fileSpace, uploadPathDB);
    }

    /**
     * 保存用户上传信息
     *
     * @param material
     * @return
     */
    @PostMapping("/uploadMaterial")
    public ControllerJSONResult uploadMaterial(@RequestBody Material material) {
        ServiceResult<Boolean> serviceResult = materialsService.insMaterial(material);
        return serviceResult.isSuccess() ? result.ok(serviceResult.isSuccess()) : result.error(result.getCode(), result.getMsg());
    }

    /**
     * 获取当前目录下图片
     * @param page
     * @param url
     * @return
     */
    @GetMapping("/getMaterialImg")
    public ControllerJSONResult getMaterialImg(@RequestParam(value = "page",defaultValue = "1") Integer page,String url) {
        // 遍历当前url路径
        ServiceResult<String[]> serviceResult = materialsService.getMaterialImg(page,url);
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(result.getCode(), result.getMsg());
    }

}
