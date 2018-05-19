package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.mapper.UserMapper;
import com.yanhuo.sign.dal.mapper.ext.UserExtMapper;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.enums.AuditEnum;
import com.yanhuo.sign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 用户业务逻辑处理
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
    public User login(User user) {
        if (null == user) {
            log.error("参数错误");
        }
        try {
            user = userExtMapper.login(user.getuName(),user.getuPwd());
            if (StringUtils.isEmpty(user.getuName()) || StringUtils.isEmpty(user.getuPwd())) {
                log.error("对象不能为空");
                return new User();
            }
        } catch (Exception e) {
            log.error("登录失败");
        }

        if ((!user.getuName().equals(user.getuName()) || (!user.getuPwd().equals(user.getuPwd())))) {
            log.error("用户名或密码错误");
            return new User();
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
        int u = userMapper.insert(user);
        if (u < 0) {
            log.error("注册失败");
            return 0;
        }
        AuditEnum.TO_AUDIT.getCode();
        return u;

    }

    @Override
    public List<User> selectAllTeachers() {
        List<User> users = new ArrayList<>();
        try {
            users = userExtMapper.selectAllTeachers();
        } catch (Exception e) {
            log.error("查询所有教师失败");
        }
        return users;
    }

    @Override
    public User selectTeacher(Long uId) {
        if (StringUtils.isEmpty(uId)) {
            log.error("参数为空");
            return new User();
        }
        User user = new User();
        try {
             user = userExtMapper.selectTeacher(uId);
        } catch (Exception e) {
            log.error("查询教师信息失败");
        }
        return user;
    }

    @Override
    public int deleteTeacher(Long uId) {
        if (StringUtils.isEmpty(uId)) {
            log.error("参数为空");
            return 0;
        }
        int teacher = userMapper.deleteByPrimaryKey(uId);
        if (teacher < 0) {
            log.error("删除教师失败");
            return 0;
        }
        return teacher;
    }

    @Override
    public int updateTeacherInfo(User user) {
        if (null == user) {
            log.error("参数错误");
            return 0;
        }
        int update = userExtMapper.updateTeacherInfo(user);
        if (update < 0) {
            log.error("修改个人信息失败");
            return 0;
        }
        return update;
    }
}
