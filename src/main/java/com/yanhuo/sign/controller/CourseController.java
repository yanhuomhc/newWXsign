package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.service.CourseService;
import com.yanhuo.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/teacherAdminCourseInfo", method = RequestMethod.GET)
    public String teacherAdminCourseInfo() {
        return "teacherAdminCourseInfo";
    }


    @GetMapping("/getCourse")
    @ResponseBody
    public Object getCourse(HttpSession session) {

        User user = (User) session.getAttribute("user");

        return courseService.selectAllCourse(user.getuId());

    }


    @GetMapping("/getClass")
    @ResponseBody
    public Object getClass(HttpSession session) {

        User user = (User) session.getAttribute("user");

        List<Sign> signs = signService.selectSignDetailBytId(user.getuId());
        Map<Long, Sign> ClassAndSign = new HashMap<>();
        signs.forEach(x -> {
            ClassAndSign.put(x.getsClass(),x);

        });

        List<Long> sclass=new ArrayList<>(ClassAndSign.keySet());
        return sclass;

    }


}
