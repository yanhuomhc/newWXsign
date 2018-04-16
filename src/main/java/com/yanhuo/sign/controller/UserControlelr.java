package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.mapper.UserMapper;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:15
 */
@Controller
public class UserControlelr {

    @Autowired
    private UserService userService;
//
//    /**
//     * 用户登录
//     * @param uName
//     * @param uPwd
//     * @return
//     */
//    @GetMapping(value = "/index")
//    User login(@RequestParam String uName,
//               @RequestParam String uPwd,
//               HttpSession session,
//               RedirectAttributes attributes) {
//        return userService.login(uName,uPwd,session,attributes);
//    }

    /**
     * 用户注册
     * @param user
     */
    @RequestMapping(value = "/teacherSign",method = RequestMethod.POST)
    public void register(User user) {
        userService.register(user);
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/sign",method = RequestMethod.GET)
    public String sign(){
        return "teacherSign";
    }
}
