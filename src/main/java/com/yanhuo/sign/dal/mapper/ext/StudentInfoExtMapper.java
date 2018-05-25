package com.yanhuo.sign.dal.mapper.ext;

import com.yanhuo.sign.dal.mapper.StudentInfoMapper;
import com.yanhuo.sign.dal.model.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentInfoExtMapper extends StudentInfoMapper {

    /**
     * 根据班级查询所有学生
     * @param Sclass
     * @return
     */
    List<StudentInfo> selectAllbyClass(Long Sclass);

    StudentInfo selectBySno(String sno);



}