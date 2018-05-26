package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
