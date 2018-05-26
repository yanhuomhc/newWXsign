package com.yanhuo.sign.dal.mapper.ext;

import com.yanhuo.sign.dal.mapper.StudentInfoMapper;
import com.yanhuo.sign.dal.model.StudentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentInfoExtMapper extends StudentInfoMapper {

    /**
     * 根据班级查询所有学生
     * @param Sclass
     * @return
     */
    List<StudentInfo> selectAllbyClass(Long Sclass);

    /**
     * 根据学生学号查询
     * @param sno
     * @return
     */
    StudentInfo selectBySno(String sno);


    /**
     * 查询所有学生人数
     * @param
     * @return
     */
    Integer selectCoutBytIdAndPage();

    /**
     * 查询所有学生按分页查询
     * @param page
     * @param limit
     * @return
     */

    List<StudentInfo> selectAllBytIdAndPage(@Param("page") Integer page, @Param("limit") Integer limit);



}