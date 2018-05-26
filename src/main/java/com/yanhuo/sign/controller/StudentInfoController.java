package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import com.yanhuo.sign.dal.model.Course;
import com.yanhuo.sign.dal.model.StudentInfo;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.service.StudentInfoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:15
 */
@Controller
public class StudentInfoController {

    @Autowired
    private StudentInfoExtMapper studentInfoExtMapper;
    @Autowired
    private StudentInfoService studentInfoService;

    @RequestMapping(value = "/teacherAdminStudentInfo", method = RequestMethod.GET)
    public String teacherAdminStudentInfo() {
        return "teacherAdminStudentInfo";
    }


    @RequestMapping(value = "/StudentSign", method = RequestMethod.GET)
    public String StudentSign() {
        return "StudentSign";
    }


    @RequestMapping(value = "/getSname", method = RequestMethod.GET)
    @ResponseBody
    public String getSname(@Param("sId") Integer sId) {

        return studentInfoExtMapper.selectByPrimaryKey(sId).getsName();
    }


    @GetMapping("/getStudentByPage")
    @ResponseBody
    public Object getCourseByPage(HttpSession session, @RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "limit",required = false) Integer limit) {

        User user = (User) session.getAttribute("user");

        return studentInfoService.selectAllStudentByPage(user.getuId(), page,limit);

    }


    @GetMapping("/delStudent")
    @ResponseBody
    public Object delStudent(HttpSession session, @RequestParam(value = "sId") Integer sId) {

        User user = (User) session.getAttribute("user");
        if (user==null){
            return -1;
        }

        return studentInfoExtMapper.deleteByPrimaryKey(sId);

    }


    //UpdateStudent

    @GetMapping("/UpdateStudent")
    @ResponseBody
    public Object UpdateStudent(HttpSession session, @RequestParam("sName") String sName, @RequestParam("sId") Integer sId) {

        User user = (User) session.getAttribute("user");

        StudentInfo studentInfo = studentInfoExtMapper.selectByPrimaryKey(sId);
        studentInfo.setsName(sName);
        return studentInfoExtMapper.updateByPrimaryKey(studentInfo);


    }

}
