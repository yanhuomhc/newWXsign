package com.yanhuo.sign.controller;

import com.yanhuo.sign.service.CourseService;
import com.yanhuo.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:15
 */
@Controller
public class SignController {

    @Autowired
    private SignService signService;

    @Autowired
    private CourseService courseService;

    /**
     * 教师创建签到页面
     * @return
     */
    @GetMapping(value = "/teacherAdmin")
    public String teacherAdmin() {
        return "teacherAdmin";
    }

//
//    /**
//     * 教师 签到明细查询页面
//     * @return
//     */
//    @RequestMapping(value = "/teacherAdminSignInfo",method = RequestMethod.GET)
//    public String teacherAdminSignInfo() {
//        return "teacherAdminSignInfo";
//    }
//
//
//    /**
//     * 签到结果汇总统计页面
//     * @return
//     */
//    @RequestMapping(value = "/teacherAdminSignDetail",method = RequestMethod.GET)
//    public String teacherAdminSignDetail() {
//        return "teacherAdminSignDetail";
//    }
//
//
//
//    /**
//     * 课程管理页面
//     * @return
//     */
//    @RequestMapping(value = "/teacherAdminCourseInfo",method = RequestMethod.GET)
//    public String teacherAdminCourseInfo() {
//        return "teacherAdminCourseInfo";
//    }
//
//
//    /**
//     * 学生信息导入页面
//     * @return
//     */
//    @RequestMapping(value = "/teacherAdminStudentInfo",method = RequestMethod.GET)
//    public String teacherAdminStudentInfo() {
//        return "teacherAdminStudentInfo";
//    }

}
