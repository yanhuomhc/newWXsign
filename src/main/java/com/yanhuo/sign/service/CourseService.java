package com.yanhuo.sign.service;

import com.yanhuo.sign.dal.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 课程类接口
 * @date 2018/3/20 下午4:27
 */
public interface CourseService {

    /**
     * 教师
     * 查询课程
     * @return
     */
    List<Course> selectAllCourse();

    /**
     * 教师
     * 增加课程
     * @param course
     * @return
     */
    int insertCourse(Course course);
}
