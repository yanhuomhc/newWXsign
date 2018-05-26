package com.yanhuo.sign.service;

import com.yanhuo.sign.dal.model.Course;
import com.yanhuo.sign.utils.PageResult;

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
    List<Course> selectAllCourse(Long tId);

    /**
     * 教师
     * 增加课程
     * @param course
     * @return
     */
    int insertCourse(Course course);

    /**
     * 查询课程 带分页
     * @param tId
     * @param page
     * @param limit
     * @return
     */
    PageResult<Course> selectAllCourseByPage(Long tId,Integer page,Integer limit);
}
