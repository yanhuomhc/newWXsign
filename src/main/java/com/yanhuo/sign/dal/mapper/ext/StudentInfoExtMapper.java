package com.yanhuo.sign.dal.mapper.ext;

import com.yanhuo.sign.dal.mapper.StudentInfoMapper;
import com.yanhuo.sign.dal.model.StudentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentInfoExtMapper extends StudentInfoMapper {

    List<StudentInfo> selectAllbyClass(Long Sclass);
    StudentInfo selectBySno(String sno);



}