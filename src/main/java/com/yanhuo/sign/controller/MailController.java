package com.yanhuo.sign.controller;


import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.dal.model.StudentInfo;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class MailController {

    @Autowired
    private SignService signService;
    @Autowired
    private  StudentInfoExtMapper studentInfoExtMapper;


    private int send(String recevice, StringBuffer content,Integer signNo) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost("smtp.qq.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("1634447610@qq.com");
        javaMailSender.setPassword("wdaroevmsgpzghjf");
        Properties properties = new Properties();
        properties.setProperty("mail.host", "smtp.qq.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");

        javaMailSender.setJavaMailProperties(properties);
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
            helper.setFrom("1634447610@qq.com");// 设置发件人
            helper.setTo(recevice);// 设置收件人
            helper.setSubject("第"+signNo+"次点到情况统计邮件");// 设置主题
            helper.setText(content.toString());// 邮件体
            javaMailSender.send(mailMessage);// 发送邮件


        } catch (Exception e) {
            System.out.println("发送失败" + e.fillInStackTrace().getMessage());
            return -1;
        }


        return 1;
    }


    @GetMapping("/sendEmail")
    @ResponseBody
    public Integer sendEmail(@RequestParam("SignNo") Integer SignNo, HttpSession session) {


        User user = (User) session.getAttribute("user");
        List<Sign> signList = signService.selectSignDetailBysignNum(user.getuId().intValue(), SignNo);

        if (CollectionUtils.isEmpty(signList)){
            return -1;
        }


        List<StudentInfo> absenceMen = new ArrayList<>();

        signList.forEach(x -> {
            if (!x.getIsSign()){
                StudentInfo studentInfo= studentInfoExtMapper.selectByPrimaryKey(x.getsId().intValue());
                absenceMen.add(studentInfo);
            }

        });

        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD:hh:mm:ss");

        StringBuffer content=new StringBuffer();

       String text= "第"+SignNo+"次点到结果。"+"总共"+signList.size()+"人。"+"共"+absenceMen.size()+"人缺席。"+
                "点到课程："+signList.get(0).getcName()+"。点到地点："+signList.get(0).getcLocation()+"。点到时间:"+
                sdf.format(signList.get(0).getBeginSignTime());
        content.append(text);

        for (StudentInfo absenceMAN : absenceMen) {


            content.append("缺席学生姓名"+absenceMAN.getsName()+"。学号"+absenceMAN.getsNo());
        }


        return send(user.getuEmail(), content,SignNo);

    }


}
