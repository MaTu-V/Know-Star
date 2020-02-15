package com.wd.zykt.mapper;


import com.wd.zykt.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface UserMapper {
    /**
     * 通过openid查找用户
     *
     * @param openId
     * @return
     */
    public User selUserByOpenId(String openId);

    /**
     * 通过id查找用户
     *
     * @param id
     * @return
     */
    public User selUserById(String id);

    /**
     * 查询全部用户
     *
     * @return
     */
    public LinkedList<User> selAllUser();

    /**
     * 添加当前用户
     */
    public int insUser(User user);

    /**
     * 更新用户信息
     * @param id
     * @return
     */
    public int updUseById(String id);


}
