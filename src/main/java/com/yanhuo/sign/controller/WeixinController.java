package com.yanhuo.sign.controller;


import com.google.gson.Gson;
import com.yanhuo.sign.dal.DTO.OpenId;
import com.yanhuo.sign.dal.mapper.ext.SignExtMapper;
import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import com.yanhuo.sign.dal.mapper.ext.TeacherSignRecordExtMapper;
import com.yanhuo.sign.dal.mapper.ext.UserExtMapper;
import com.yanhuo.sign.dal.model.StudentInfo;
import com.yanhuo.sign.dal.model.TeacherSignRecord;
import com.yanhuo.sign.dal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;


@Controller
@RequestMapping("/weixin")
public class WeixinController {

    @Autowired
    private UserExtMapper userExtMapper;
    @Autowired
    private SignExtMapper signExtMapper;
    @Autowired
    private StudentInfoExtMapper studentInfoExtMapper;
    @Autowired
    private TeacherSignRecordExtMapper teacherSignRecordExtMapper;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WeixinController.class);
    Gson gson = new Gson();

    @GetMapping("/auth")
    public String auth(@RequestParam("code") String code, @RequestParam("state") String state) {
        log.info("进入auth方法");
        log.info("code={}", code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                     "appid=wxcbf93ad8adfe0f7c&secret=eba0c79287b7c0337e9e31b095a776c3&code="
                   + code + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}", response);

        //字符串反序列化
        OpenId openId = gson.fromJson(response, OpenId.class);
        log.info("openId={}", openId.getOpenid());
        log.info("state={}", state);

        LocalDateTime localDateTime = LocalDateTime.now();
        Long now = localDateTime.toInstant(ZoneOffset.of("+8")).getEpochSecond();

        String[] strings = state.split("\\|");
        Integer tId = Integer.valueOf(strings[1]);


        Long State = Long.valueOf(strings[0]);
        if (now - State > 5) {
            //返回到错误提示页面
            return "redirect:/weixin/SignError";

        } else {

            String SopenId = openId.getOpenid();
            User user = userExtMapper.selectByOpenId(SopenId);
            if (user == null) {
                //重定向到绑定身份的界面
                return "redirect:/weixin/Bind?openId=" + SopenId;
            } else {
                StudentInfo studentInfo = studentInfoExtMapper.selectBySno(user.getsNo());
                TeacherSignRecord teacherSignRecord = teacherSignRecordExtMapper.selectBytId(tId);
                //更新签到状态
                signExtMapper.updateSignStatus(studentInfo.getsId().longValue(), teacherSignRecord.getSignRecordNo(), tId, new Date());
            }
            //成功提示页面
            return "redirect:/weixin/Success";

        }
    }


    /**
     * 错误页面
     */
    @GetMapping("/SignError")
    public String SignError() {
        return "student/SignError";
    }

    /**
     * 签到成功页面
     */
    @GetMapping("/Success")
    public String Success() {
        return "student/Success";
    }


    /**
     * 身份绑定页面
     */
    @GetMapping("/Bind")
    public String Bind(@RequestParam("openId") String openId, Model model) {
        model.addAttribute("openId", openId);

        return "student/StudentSign";
    }

    @PostMapping("/doBind")
    public String doBind(User user) {
        user.setuPower(3);
        userExtMapper.insert(user);
        System.out.println(user.toString());
        return "student/newBind";

    }

}
