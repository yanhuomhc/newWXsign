package com.yanhuo.sign.controller;


import com.yanhuo.sign.dal.mapper.ext.SignExtMapper;
import jxl.read.biff.BiffException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;


import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Controller
public class MailController {

    @Autowired
    private SignExtMapper signExtMapper;



    private  void send(){
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
            helper.setFrom("点到系统邮件");// 设置发件人
            helper.setTo("xingchen@maihaoche.com");// 设置收件人
//            helper.setCc(cc);// 设置抄送
            helper.setSubject("本次点到情况");// 设置主题


            String content="sss"+"|"+"ssss"+"|"+"sss";

            helper.setText("content");// 邮件体
            javaMailSender.send(mailMessage);// 发送邮件
          //  Log.info("邮件发送成功...");
        } catch (Exception e) {
          //  Log.info("邮件发送发生异常:" + e.getMessage());

        }



    }



    public static void main(String args[]) throws BiffException, IOException {

        MailController mailController=new MailController();

        mailController.send();


    }

}
