package com.yanhuo.sign.controller;

import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import com.yanhuo.sign.service.CourseService;
import com.yanhuo.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:15
 */
@Controller
public class SignController {



    /**
     * 签到明细查询页面
     * @return
     */
    @RequestMapping("selectSignDetail")
    public String selectSignDetail() {
        return "teacher/querySignDetail";
    }

    /**
     * 签到结果汇总页面
     * @return
     */
    @RequestMapping("signResultAccount")
    public String signResultAccount() {
        return "teacher/SignResultAccount";
    }

    /**
     * 课程管理页面
     * @return
     */
    @RequestMapping("courseInfo")
    public String courseInfo() {
        return "teacher/teacherAdminCourseInfo";
    }

}
