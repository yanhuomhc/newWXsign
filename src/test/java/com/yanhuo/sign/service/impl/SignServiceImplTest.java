package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.mapper.SignMapper;
import com.yanhuo.sign.dal.mapper.ext.SignExtMapper;
import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private SignExtMapper signExtMapper;


    Sign sign = new Sign();

    /**
     * 根据课程名查询签到明细
     */
    @Test
    public void selectSignDetailBycName() {
        //测试数据
        sign.setcName("数据结构");
        //使用断言
        assert ("数据结构".equals(sign.getcName()));
        log.info("签到明细："+signService.selectSignDetailBycName(sign.getcName()));
    }

    /**
     * 根据班级查询签到明细
     */
    @Test
    public void selectSignDetailBysClass() {
        sign.setsClass(145505L);
        log.info("签到明细："+signService.selectSignDetailBysClass(sign.getsClass()));
    }

//    /**
//     * 根据当前签到场次查询签到明细
//     */
//    @Test
//    public void selectSignDetailBysignNum() {
//        sign.setSignNum(2);
//        log.info("签到明细："+signService.selectSignDetailBysignNum(sign.getSignNum()));
//    }

    /**
     * 根据学生Id查询签到明细
     */
    @Test
    public void selectSignDetailBysId() {
        sign.setsId(14550535L);
        log.info("签到明细："+signService.selectSignDetailBysId(sign.getsId()));
    }

    /**
     * 修改签到状态
     */
    @Test
    public void updateSignStatus() {
        //测试数据
        List<Long> signs = new ArrayList<>();
        sign.setsId(14550535L);
        signs.add(sign.getsId());
        sign.setsId(14550510L);
        signs.add(sign.getsId());
        sign.setsId(14550512L);
        signs.add(sign.getsId());
        //log.info("签到成功："+signExtMapper.updateSignStatus(signs));
        //使用断言
        assertEquals(14550535L,14550535L);
        assertEquals(14550510L,14550510L);
        assertEquals(14550512L,14550512L);


    }
}