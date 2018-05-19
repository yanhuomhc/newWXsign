package com.yanhuo.sign.dal.mapper.ext;

import com.yanhuo.sign.dal.mapper.SignMapper;
import com.yanhuo.sign.dal.model.Sign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface SignExtMapper extends SignMapper {

    /**
     * 教师权限：
     * 根据课程名查询签到明细
     * @param cName
     * @return
     */
    List<Sign> selectCourseBycName(String cName);

    /**
     * 教师权限
     * 根据班级查询签到明细
     * @param sClass
     * @return
     */
    List<Sign> selectSignDetailBysClass(Long sClass);

    /**
     * 教师权限：
     * 根据当前签到场次查询签到明细
     * @param signNum
     * @return
     */
    List<Sign> selectCourseBysignNum(int signNum);

    /**
     * 学生权限：
     * 通过学生Id查询签到明细
     * @param sId
     * @return
     */
    List<Sign> selectSignDetailBysId(Long sId);

    /**
     * 学生签到后，修改签到状态
     * @param sId
     * @return
     */
    int updateSignStatus(List<Long> sId);




}
