package com.yanhuo.sign.dal.mapper.ext;


import com.yanhuo.sign.dal.mapper.CourseMapper;
import com.yanhuo.sign.dal.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseExtMapper extends CourseMapper {

    List<Course> selectAllBytId(Long tId);

    Integer selectCoutBytIdAndPage(Long tId);

    List<Course> selectAllBytIdAndPage(@Param("tId") Long tId, @Param("page") Integer page, @Param("limit") Integer limit);
}

