package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.mapper.UserMapper;
import com.yanhuo.sign.dal.mapper.ext.UserExtMapper;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.enums.AuditEnum;
import com.yanhuo.sign.enums.PowerEnum;
import com.yanhuo.sign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserExtMapper userExtMapper;

    @Override
    public User login(User user) {
        if (ObjectUtils.isEmpty(user)) {
            log.error("参数错误");
        }
        user = userExtMapper.login(user.getuName(), user.getuPwd());

        if (user == null) {
            log.error("数据库没有该记录");
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
        user.setuPower(PowerEnum.TEACHER.getCode());
        int u = userMapper.insert(user);
        if (u < 0) {
            log.error("注册失败");
            return 0;
        }

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
