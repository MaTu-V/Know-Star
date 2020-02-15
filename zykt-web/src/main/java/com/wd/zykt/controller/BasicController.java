package com.wd.zykt.controller;

import com.wd.zykt.pojo.User;
import com.wd.zykt.pojo.VO.UserVO;
import com.wd.zykt.utils.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class BasicController {

    @Autowired
    ControllerJSONResult result;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ReadProteriesUtil readProteriesUtil;

    /**
     * 放入缓存
     * @param user
     * @return
     */
    public UserVO setUserRedisSessionToken(User user){
        //设置唯一token
        String uniqueToken = UUID.randomUUID().toString();
        //将用户id与token相对应
        redisUtil.set(Constant.RedisCode.USER_REDIS_SESSION + ":"+ user.getId(),uniqueToken);
        //创建新的实体类（包含token）
        UserVO userVO = new UserVO();
        //将原本的user 保存到 新的UserVO中 返回新的user
        BeanUtils.copyProperties(user,userVO);
        userVO.setUniqueToken(uniqueToken);
        return userVO;
    }

    /**
     * 资源文件上传
     * @param file
     * @param fileSpace
     * @param uploadPathDB
     * @return
     */
    public ControllerJSONResult uploadResources(MultipartFile file, String fileSpace, String uploadPathDB) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (file != null) {
                //获取初始文件名称
                String FileName = file.getOriginalFilename();
                //文件名不为空
                if (!StringUtils.isEmpty(FileName)) {
                    // 文件上传的最终保存路径
                    String finalPath = readProteriesUtil.FILE_SPACE + fileSpace + uploadPathDB + "/" + FileName;
                    // 设置数据库保存的路径
                    uploadPathDB = fileSpace + uploadPathDB + "/" + FileName;
                    File outFile = new File(finalPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                }
            }
            //上传成功后返回文件路径
            return result.ok(uploadPathDB);
        } catch (Exception e) {
            e.printStackTrace();
            return result.error();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

