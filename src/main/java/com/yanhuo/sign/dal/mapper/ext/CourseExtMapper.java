package com.yanhuo.sign.dal.mapper.ext;


import com.yanhuo.sign.dal.mapper.CourseMapper;
import com.yanhuo.sign.dal.model.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseExtMapper extends CourseMapper {

    List<Course> selectAllBytId(Long tId);

}