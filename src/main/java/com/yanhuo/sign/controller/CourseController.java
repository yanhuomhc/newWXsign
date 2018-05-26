package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.mapper.ext.CourseExtMapper;
import com.yanhuo.sign.dal.model.Course;
import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.service.CourseService;
import com.yanhuo.sign.service.SignService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:15
 */
@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private SignService signService;
    @Autowired
    private CourseExtMapper courseExtMapper;

    @RequestMapping(value = "/teacherAdminCourseInfo", method = RequestMethod.GET)
    public String teacherAdminCourseInfo() {
        return "teacher/teacherAdminCourseInfo";
    }


    @GetMapping("/getCourse")
    @ResponseBody
    public Object getCourse(HttpSession session) {

        User user = (User) session.getAttribute("user");

        return courseService.selectAllCourse(user.getuId());

    }


    @GetMapping("/delCourse")
    @ResponseBody
    public Object delCourse(HttpSession session, @Param("cId") Long cId) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return -1;
        }


        return courseExtMapper.deleteByPrimaryKey(cId);

    }


    @GetMapping("/getCourseByPage")
    @ResponseBody
    public Object getCourseByPage(HttpSession session, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "limit",required = false) Integer limit) {

        User user = (User) session.getAttribute("user");

        return courseService.selectAllCourseByPage(user.getuId(), page, limit);

    }


    @GetMapping("/UpdateCourse")
    @ResponseBody
    public Object UpdateCourse(HttpSession session, @RequestParam("cName") String cName, @RequestParam("cId") Long cId) {

        User user = (User) session.getAttribute("user");

        Course course = courseExtMapper.selectByPrimaryKey(cId);
        course.setcName(cName);
        return courseExtMapper.updateByPrimaryKey(course);


    }


    @PostMapping("/addCourse")
    public String addCourse(@RequestParam("cname") String cname, HttpSession session) {
        Course course = new Course();
        course.setcName(cname);

        User user = (User) session.getAttribute("user");

        course.settId(user.getuId());
        courseService.insertCourse(course);

        return "redirect:/courseInfo";
    }


    //addCourse


    @GetMapping("/getClass")
    @ResponseBody
    public Object getClass(HttpSession session) {

        User user = (User) session.getAttribute("user");

        List<Sign> signs = signService.selectSignDetailBytId(user.getuId());
        Map<Long, Sign> ClassAndSign = new HashMap<>();
        signs.forEach(x -> {
            ClassAndSign.put(x.getsClass(), x);

        });

        List<Long> sclass = new ArrayList<>(ClassAndSign.keySet());
        return sclass;

    }


}
