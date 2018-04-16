package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.model.User;
import lombok.extern.slf4j.Slf4j;
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
        User user = new User();
        user.setuName("烟火");
        user.setuPwd("111111");
        user.setuPower(3);
        user.setuEmail("yanhuo@maihaoche.com");
        //user.setuSex(2);
        user.setuInstitute("信息工程学院");
        log.info("注册成功:"+userService.register(user));
    }

    /**
     * 用户登录测试
     */
    @Test
    public void login() {
        User user = userService.login("烟火","111111");
        log.info("登录成功：用户邮箱为"+user.getuEmail());

    }
}