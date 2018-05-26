package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TeacherController {

    @GetMapping("/toTeacherInfo")
    public  String toTeacherInfo(HttpSession session){

        User user=(User)session.getAttribute("user");

        if (user==null){
            return "redirect:/index";
        }


        return  "admin/TeacherInfo";

    }
}
