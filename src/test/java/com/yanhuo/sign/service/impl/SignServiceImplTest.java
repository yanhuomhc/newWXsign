package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/16 下午8:20
 */
@SpringBootTest
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
public class SignServiceImplTest {

    @Autowired
    private SignService signService;


    Sign sign = new Sign();

    /**
     * 根据课程名查询签到明细
     */
    @Test
    public void selectSignDetailBycName() {
        sign.setcName("数据结构");
        log.info("签到明细："+signService.selectSignDetailBycName(sign.getcName()));
    }

    /**
     * 根据班级查询签到明细
     */
    @Test
    public void selectSignDetailBysClass() {
        sign.setsClass(145501L);
        log.info("签到明细："+signService.selectSignDetailBysClass(sign.getsClass()));
    }

    /**
     * 根据当前签到场次查询签到明细
     */
    @Test
    public void selectSignDetailBysignNum() {
        sign.setSignNum(2);
        log.info("签到明细："+signService.selectSignDetailBysignNum(sign.getSignNum()));
    }
}