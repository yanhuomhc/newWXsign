package com.yanhuo.sign.dal.mapper.ext;

import com.yanhuo.sign.dal.mapper.SignMapper;
import com.yanhuo.sign.dal.model.Sign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SignExtMapper extends SignMapper {

    /**
     * 根据课程名查询签到明细
     * @param cName
     * @return
     */
    List<Sign> selectCourseBycName(String cName);

    /**
     * 根据班级查询签到明细
     * @param sClass
     * @return
     */
    List<Sign> selectSignDetailBysClass(Long sClass);

    /**
     * 根据当前签到场次查询签到明细
     * @param signNum
     * @return
     */
    List<Sign> selectCourseBysignNum(int signNum);




}
