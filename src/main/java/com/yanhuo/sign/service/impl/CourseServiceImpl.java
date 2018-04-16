package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.mapper.CourseMapper;
import com.yanhuo.sign.dal.model.Course;
import com.yanhuo.sign.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 课程管理逻辑处理
 * @date 2018/4/14 下午1:13
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> selectAllCourse() {

        List<Course> courses = new ArrayList<>();
        try {
             courses = courseMapper.selectAll();
        } catch (Exception e) {
            log.error("查询课程失败");
        }
        return courses;
    }

    @Override
    public int insertCourse(Course course) {
        if (null == course) {
            return 0;
        }

        Integer c = null;
        try {
            c = courseMapper.insert(course);
        } catch (Exception e) {
            log.error("新增课程失败");
        }
        if (c < 0) {
            return 0;
        }
        return c;
    }
}
