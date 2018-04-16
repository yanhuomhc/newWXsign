package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/16 下午7:06
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CourseServiceImplTest {

    @Autowired
    private CourseServiceImpl courseService;

    @Test
    public void selectCourse() {
       // Course course = new Course();
        //course.setcId(1L);
        log.info("查询课程为："+courseService.selectAllCourse());
    }

    @Test
    public void  insertCourse() {
        Course course = new Course();
        course.setcName("计算机科学与技术");
        course.settId(2L);
        log.info("新增课程为："+courseService.insertCourse(course));
    }

}