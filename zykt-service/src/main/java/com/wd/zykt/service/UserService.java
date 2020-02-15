package com.wd.zykt.service;


import com.wd.zykt.pojo.User;
import com.wd.zykt.utils.ServiceResult;

import java.util.LinkedList;

public interface UserService {
    /**
     * 查询当前用户
     */
    public ServiceResult<User> QueUserByOpenId(String openId);

    /**
     * 查询全部用户
     *
     * @return
     */
    public ServiceResult<LinkedList<User>> QueryAllUser();


    /**
     * 插入当前用户
     *
     * @return
     */
    public ServiceResult<Boolean> InsUser(User user);

    /**
     * 查询当前用户个人信息
     * @param id
     * @return
     */
    public ServiceResult<User> QueUserById(String id);


    /**
     * 更新用户信息
     * @param id
     * @return
     */
    public ServiceResult<Boolean> updUseById(String id);
}
