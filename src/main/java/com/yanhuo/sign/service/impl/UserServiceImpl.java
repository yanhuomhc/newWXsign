package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.mapper.UserMapper;
import com.yanhuo.sign.dal.mapper.ext.UserExtMapper;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:14
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtMapper userExtMapper;

    @Override
    public User login(String uName, String uPwd) {
        if (StringUtils.isEmpty(uName) || StringUtils.isEmpty(uPwd)) {
            log.error("用户名或密码不能为空！");
            return null;
        }

        User user = null;
        try {
            user = userExtMapper.login(uName,uPwd);
        } catch (Exception e) {
            log.error("登录失败");
        }
        if (null == user) {
            log.error("对象不能为空！");
            return  null;
        }
        if ((!uName.equals(user.getuName()) || (!uPwd.equals(user.getuPwd())))) {
            log.error("用户名或密码错误");
            return null;
        }
        return user;
    }

    @Override
    public int register(User user) {
        if (null == user) {
            log.error("参数错误");
            return 0;
        }
        int uPower = 3;
        if (user.getuPower() == uPower) {
            log.error("管理员不能进行注册！");
            return 0;
        }
        Integer u = userMapper.insert(user);
        if (u < 0) {
            log.error("注册失败");
            return 0;
        }
        return u;

    }
}
