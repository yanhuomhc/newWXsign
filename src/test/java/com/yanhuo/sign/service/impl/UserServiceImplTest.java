package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.model.User;
import lombok.extern.slf4j.Slf4j;
import  static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 用户单元测试类
 * @date 2018/4/16 上午12:00
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册测试
     */
    @Test
    public void register() {
        //测试数据
        User user = new User();
        user.setuName("烟火");
        user.setuPwd("111111");
        user.setuPower(3);
        user.setuEmail("yanhuo@maihaoche.com");
        user.setuSex(false);
        user.setuInstitute("信息工程学院");
        log.info("注册成功:"+userService.register(user));
    }

    /**
     * 用户登录测试
     */
    @Test
    public void login() {
        //测试数据
        User user = new User();
        user.setuName("烟火");
        user.setuPwd("111111");
        user.setuEmail("yanhuo@maihaoche.com");
        log.info("登录成功：用户邮箱为"+user.getuEmail());
    }





    @Test
    public void selectAllTeachers() {
        log.info("所有教师细信息："+userService.selectAllTeachers());
    }

    @Test
    public void selectTeacher() {
        User user = new User();
        user.setuId(2L);
        log.info("该教师信息如下："+userService.selectTeacher(user.getuId()));
    }

    @Test
    public void deleteTeacher() {
        User user = new User();
        user.setuId(3L);
        log.info("已删除该教师："+userService.deleteTeacher(user.getuId()));
    }

    @Test
    public void updateUPwd() {
        User user = new User();
        user.setuId(2L);
        user.setuPwd("000");
        user.setuName("杨杨");
        user.setuEmail("yangyang@maihaoche.com");
        user.setuSex(true);
        user.setuInstitute("环境科学学院");
        user.setuPower(2);
        log.info("修改密码成功"+userService.updateTeacherInfo(user));
    }
}