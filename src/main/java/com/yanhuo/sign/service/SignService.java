package com.yanhuo.sign.service;

import com.yanhuo.sign.dal.model.Sign;

import java.util.List;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/3/20 下午4:28
 */
public interface SignService {

    /**
     * 根据课程名查询签到明细
     * @param cName
     * @return
     */
    List<Sign> selectSignDetailBycName(String cName);

    /**
     * 根据班级查询签到明细
     * @param sClass
     * @return
     */
    List<Sign> selectSignDetailBysClass(Long sClass);

    /**
     * 根据当前签到次数查询签到明细
     * @param signNum
     * @return
     */
    List<Sign> selectSignDetailBysignNum(int signNum);



}
