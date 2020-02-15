package com.wd.zykt.service.Impl;

import com.wd.zykt.mapper.UserMapper;
import com.wd.zykt.pojo.User;
import com.wd.zykt.service.UserService;
import com.wd.zykt.utils.Constant;
import com.wd.zykt.utils.ServiceResult;
import com.wd.zykt.utils.ServiceResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 通过openid查找用户
     *
     * @param openId
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ServiceResult<User> QueUserByOpenId(String openId) {
        if (StringUtils.isEmpty(openId)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        return ServiceResultHelper.genResultWithDataBaseSuccess(userMapper.selUserByOpenId(openId));
    }

    /**
     * 查询全部用户
     *
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Cacheable(cacheNames = Constant.RedisCode.USER_ALL_REDIS_SESSION)
    @Override
    public ServiceResult<LinkedList<User>> QueryAllUser() {
        return ServiceResultHelper.genResultWithDataBaseSuccess(userMapper.selAllUser());
    }

    /**
     * 添加当前用户
     *
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServiceResult<Boolean> InsUser(User user) {
        if (StringUtils.isEmpty(user)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        user.setId(UUID.randomUUID().toString());
        if (userMapper.insUser(user) > 0) {
            return ServiceResultHelper.genResultWithLoginSuccess();
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }

    /**
     * 通过id查找用户
     *
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServiceResult<User> QueUserById(String id) {
        // 参数不正确
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        // 查询用户信息
        User user = userMapper.selUserById(id);
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(user.getTime()));
            // 当前登入时间和上次登入时间相比
            if (System.currentTimeMillis() - c.getTimeInMillis() > 86400000){
                userMapper.updUseById(user.getId());
                user.setEnergy(user.getEnergy()+ 2);
            }
            return ServiceResultHelper.genResultWithDataBaseSuccess(user);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }

    /**
     * 更新用户信息
     *
     * @param id
     * @return
     */
    @Override
    public ServiceResult<Boolean> updUseById(String id) {
        if (StringUtils.isEmpty(id)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        if (userMapper.updUseById(id) > 0){
            return ServiceResultHelper.genResultWithDataBaseSuccess();
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }
}
