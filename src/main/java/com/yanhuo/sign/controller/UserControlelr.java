package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.VO.SignVO;
import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import com.yanhuo.sign.dal.mapper.ext.UserExtMapper;
import com.yanhuo.sign.dal.model.Course;
import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.dal.model.StudentInfo;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.enums.PowerEnum;
import com.yanhuo.sign.service.SignService;
import com.yanhuo.sign.service.UserService;
import com.yanhuo.sign.utils.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private SignService signService;
    @Autowired
    private StudentInfoExtMapper studentInfoExtMapper;


    @RequestMapping("toLogin")
    public String toLogin() {
        return "/index";
    }

    /**
     * 登录
     *
     * @param uName
     * @param uPwd
     * @param model
     * @param session
     * @return
     */
    @PostMapping(value = "/teacherAdmin")
    public String login(@RequestParam("uName") String uName, @RequestParam("uPwd") String uPwd, Model model, HttpSession session) {

        User user = new User();
        user.setuName(uName);
        user.setuPwd(uPwd);

        User userLogin = userService.login(user);
        if (null == userLogin) {
            log.error("系统异常");
            model.addAttribute("error", "用户名或密码错误，请重新登录");
            return "index";
        }
        session.setAttribute("user", userLogin);
        if (userLogin.getuPower().equals(PowerEnum.STUDENT.getCode())) {
            return "redirect:toStudentSignInfo";
        }
        if (userLogin.getuPower().equals(PowerEnum.TEACHER.getCode())) {
            return "redirect:toTeacherAdmin";
        }

        return "redirect:toAdminTeacherInfo";

    }

    @GetMapping("/toAdminTeacherInfo")
    public String toAdminTeacherInfo(HttpSession session, Model model) {


        return "admin/AdminTeacherInfo";
    }




    @GetMapping("/toStudentSignInfo")
    public String toStudentSignInfo(HttpSession session, Model model) {

        User student = (User) session.getAttribute("user");
        StudentInfo studentInfo = studentInfoExtMapper.selectBySno(student.getsNo());


        List<Sign> signList = signService.selectSignDetailBysId(studentInfo.getsId().longValue());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd:HH:mm:ss");
        List<SignVO> signVOS = new ArrayList<>();
        signList.forEach(x -> {
            SignVO signVO = new SignVO();
            signVO.setBeginSignTime(simpleDateFormat.format(x.getBeginSignTime()));
            signVO.setcLocation(x.getcLocation());
            signVO.setcName(x.getcName());
            String Sign = "否";
            if (x.getIsSign()) {
                Sign = "是";
            }
            signVO.setIsSign(Sign);
            signVO.setOpenId(x.getOpenId());
            signVO.setsClass(x.getsClass());
            signVO.setSignId(x.getSignId());
            signVO.setSignNum(x.getSignNum());

            String signtime = "未签到";
            if (x.getSignTime() != null) {
                signtime = simpleDateFormat.format(x.getSignTime());
            }
            signVO.setSignTime(signtime);

            signVO.setsName(studentInfo.getsName());
            signVO.settId(x.gettId());
            signVO.settName(x.gettName());
            signVOS.add(signVO);
        });


        model.addAttribute("signVOS", signVOS);


        return "student/StudentSignInfo";
    }


    @GetMapping("/toTeacherAdmin")
    public String toTeacherAdmin(HttpSession session) {

        User teacher = (User) session.getAttribute("user");
        if (teacher.getuPower() == null || teacher.getuPower() != 2) {
            return "redirect:toLogin";
        }

        return "teacher/teacherAdmin";
    }


    /**
     * 登出
     *
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
     *
     * @param user
     * @param model
     * @return
     */
    @PostMapping(value = "/teacherRegister")
    public String register(User user, Model model) {
        userService.register(user);

        return "redirect:index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String sign() {
        return "teacherSign";
    }


    @RequestMapping(value = "/user/userChangePwd")
    public String userChangePwd(String pwd, String newPwd, String newRePwd, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/index";
        }

        if (!newPwd.equals(newRePwd)) {
            return "redirect:/toTeacherInfo";
        }

        User teacher = userExtMapper.selectByPrimaryKey(user.getuId());

        teacher.setuPwd(newPwd);
        userExtMapper.updateByPrimaryKey(teacher);


        return "admin/pwdsuccess";

    }


    /**
     * 获取教师信息
     * @return
     */
    @GetMapping("/getTeacher")
    @ResponseBody
    public  Object getTeacher(){
        List<User> users= userExtMapper.selectAllTeachers();
        PageResult pageResult=new PageResult();
        pageResult.setSuccess(Boolean.TRUE);
        pageResult.setCode(0);
        pageResult.setData(users);
        Integer count= users.size();
        pageResult.setCount(count);

        return pageResult;

    }

    /**
     * 删除用户信息
     */
    @GetMapping("/delUser")
    @ResponseBody
    public  Integer delUser(@RequestParam("uId") Long uId,HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return -1;
        }

        return userExtMapper.deleteByPrimaryKey(uId);

    }


    /**
     * 删除用户信息
     */
    @GetMapping("/UpdateUser")
    @ResponseBody
    public  Integer UpdateUser(@RequestParam("uEmail") String uEmail,@RequestParam("uId") Long uId,HttpSession session){
        User user=(User)session.getAttribute("user");
        if(user==null){
            return -1;
        }
      User teacher=  userExtMapper.selectByPrimaryKey(uId);
        teacher.setuEmail(uEmail);
        return userExtMapper.updateByPrimaryKey(teacher);

    }
}
