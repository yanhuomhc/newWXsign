package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.mapper.SignMapper;
import com.yanhuo.sign.dal.mapper.ext.SignExtMapper;
import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.service.SignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.expression.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:13
 */
@Service
@Slf4j
public class SignServiceImpl implements SignService{

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private SignExtMapper signExtMapper;

    @Override
    public List<Sign> selectSignDetailBycName(String cName) {
        if (StringUtils.isEmpty(cName)) {
            return new ArrayList<>();
        }
        List<Sign> signs = new ArrayList<>();
        try {
            signs = signExtMapper.selectCourseBycName(cName);
        } catch (Exception e) {
            log.error("查询签到明细失败");
        }
        return signs;
    }

    @Override
    public List<Sign> selectSignDetailBysClass(Long sClass) {
        if (StringUtils.isEmpty(sClass)) {
            return new ArrayList<>();
        }
        List<Sign> signs = new ArrayList<>();
        try {
            signs = signExtMapper.selectSignDetailBysClass(sClass);
        } catch (Exception e) {
            log.error("查询签到明细失败");
        }
        return signs;
    }

    @Override
    public List<Sign> selectSignDetailBysignNum(int signNum) {
        if (StringUtils.isEmpty(signNum)) {
            return new ArrayList<>();
        }
        List<Sign> signs = new ArrayList<>();
        try {
            signs = signExtMapper.selectCourseBysignNum(signNum);
        } catch (Exception e) {
            log.error("查询签到明细失败");
        }
        return signs;
    }
}
