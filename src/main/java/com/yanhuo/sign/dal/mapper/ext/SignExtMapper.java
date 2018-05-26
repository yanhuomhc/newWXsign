package com.yanhuo.sign.dal.mapper.ext;

import com.yanhuo.sign.dal.mapper.SignMapper;
import com.yanhuo.sign.dal.model.Sign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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
    List<Sign> selectCourseBysignNum(@Param("tid") Integer tid,@Param("signNum") Integer signNum);

    /**
     * 学生权限：
     * 通过学生Id查询签到明细
     * @param sId
     * @return
     */
    List<Sign> selectSignDetailBysId(Long sId);

    /**
     * 老师权限：
     * 通过老师Id查询签到明细
     * @param tId
     * @return
     */
    List<Sign>  selectSignDetailBytId(Long tId);


    /**
     * 学生签到后，修改签到状态
     * @param sId
     * @return
     */
    int updateSignStatus(@Param("sId") Long sId, @Param("no") Integer no, @Param("tId") Integer tId, @Param("signTime") Date signTime);


    /**
     * 按条件查询签到记录
     * @param currentPage
     * @param pageSize
     * @param tId
     * @param course
     * @param sClass
     * @param SignNo
     * @return
     */
    List<Sign> selectSignDetailBytCondition(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize,@Param("tId") Long tId,@Param("course") String course,@Param("sClass") Long sClass,@Param("SignNo") Integer SignNo);

    /**
     * 按条件统计签到记录
     * @param tId
     * @param course
     * @param sClass
     * @param SignNo
     * @return
     */
    Integer selectCountSignDetailBytCondition(@Param("tId") Long tId,@Param("course") String course,@Param("sClass") Long sClass,@Param("SignNo") Integer SignNo);





}
