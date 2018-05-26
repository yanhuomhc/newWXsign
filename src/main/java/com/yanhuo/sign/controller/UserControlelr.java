package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.mapper.ext.UserExtMapper;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:15
 */
@Controller
@Slf4j
public class UserControlelr {

    @Autowired
    private UserService userService;
    @Autowired
    private UserExtMapper userExtMapper;


    @RequestMapping("toLogin")
    public String toLogin() {
        return "/index";
    }

    /**
     * 登录
     * @param uName
     * @param uPwd
     * @param model
     * @param session
     * @return
     */
    @PostMapping(value = "/teacherAdmin")
    public String login(@RequestParam("uName") String uName,@RequestParam("uPwd") String uPwd,Model model,HttpSession session) {

        User user=new User();
        user.setuName(uName);
        user.setuPwd(uPwd);

        User userLogin = userService.login(user);
        if (null == userLogin) {
            log.error("系统异常");
            model.addAttribute("error","用户名或密码错误，请重新登录");
            return "index";
        }
        session.setAttribute("user",userLogin);
        return "redirect:toTeacherAdmin";
    }


    @GetMapping("/toTeacherAdmin")
    public String toTeacherAdmin(HttpSession session){

        User teacher=(User)session.getAttribute("user");
        if (teacher.getuPower()==null||teacher.getuPower()!=2){
            return "redirect:toLogin";
        }

        return  "teacher/teacherAdmin";
    }


    /**
     * 登出
     * @param session
     * @return
     */
    @GetMapping(value = "/loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("user");
        return "/index";
    }


    @RequestMapping("toRegister")
    public String toRegister() {
        return "teacher/teacherRegister";
    }

    /**
     * 注册
     * @param user
     * @param model
     * @return
     */
    @PostMapping(value = "/teacherRegister")
    public String register(User user,Model model) {
        userService.register(user);
        return "teacher/teacherRegister";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }


    @RequestMapping(value = "/sign",method = RequestMethod.GET)
    public String sign(){
        return "teacherSign";
    }


    @RequestMapping(value = "/user/userChangePwd")
    public String userChangePwd(String pwd, String newPwd, String newRePwd, HttpSession session){
        User user=(User)session.getAttribute("user");
        if (user==null){
            return "redirect:/index";
        }

        if (!newPwd.equals(newRePwd)){
            return "redirect:/toTeacherInfo";
        }

      User teacher  =userExtMapper.selectByPrimaryKey(user.getuId());

        teacher.setuPwd(newPwd);
        userExtMapper.updateByPrimaryKey(teacher);


        return "admin/pwdsuccess";

    }
}
