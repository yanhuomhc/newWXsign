package com.yanhuo.sign.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.yanhuo.sign.dal.mapper.ext.CourseExtMapper;
import com.yanhuo.sign.dal.mapper.ext.SignExtMapper;
import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import com.yanhuo.sign.dal.mapper.ext.TeacherSignRecordExtMapper;
import com.yanhuo.sign.dal.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Controller
public class QRcodeController {

    @Autowired
    private StudentInfoExtMapper studentInfoExtMapper;
    @Autowired
    private SignExtMapper signExtMapper;

    @Autowired
    private CourseExtMapper courseExtMapper;
    @Autowired
    private TeacherSignRecordExtMapper teacherSignRecordExtMapper;

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }




    @PostMapping(value = "toQRcode")
    public String toQRcode(@RequestParam("location") String location, @RequestParam("course") Integer  course, @RequestParam("Sclass") Long Sclass, HttpSession session){

        User user=(User) session.getAttribute("user");

        if (ObjectUtils.isEmpty(user)){
            return  null;//请登录
        }

       List<StudentInfo> studentInfoList= studentInfoExtMapper.selectAllbyClass(Sclass);

       if (CollectionUtils.isEmpty(studentInfoList)){
           return null;//没有学生
       }

       //获取课程名
      Course courseName=courseExtMapper.selectByPrimaryKey(course.longValue());

       //获取当前签到次数 并更新签到次数记录

        TeacherSignRecord teacherSignRecord=teacherSignRecordExtMapper.selectBytId(user.getuId().intValue());
        Integer NowNum=0;
        if (teacherSignRecord==null){
            NowNum=1;
            TeacherSignRecord record=new TeacherSignRecord();
            record.setSignRecordNo(NowNum);
            record.settId(user.getuId().intValue());
            teacherSignRecordExtMapper.insert(record);
        }else {
            NowNum=teacherSignRecord.getSignRecordNo()+1;
            teacherSignRecord.setSignRecordNo(NowNum);
            teacherSignRecordExtMapper.updateByPrimaryKey(teacherSignRecord);
        }



        for (StudentInfo studentInfo : studentInfoList) {
            Sign sign=new Sign();
            sign.setsId(studentInfo.getsId().longValue());
            sign.setcName(courseName.getcName());
            sign.setsClass(Sclass);
            sign.setSignNum(NowNum);
            sign.setBeginSignTime(new Date());
            sign.setcLocation(location);
            sign.setIsSign(false);
            sign.setOpenId(null);
            sign.setSignTime(null);
            sign.settId(user.getuId().intValue());
            sign.settName(user.getuName());

            signExtMapper.insert(sign);
        }




        return "redirect:QRcodePage";
    }

    @GetMapping(value = "QRcodePage")
    public String toQRcode(){

        return "teacher/QRcode";
    }






    @GetMapping(value = "QRCode")
    public void QRCode(HttpServletResponse response,HttpSession session) throws IOException {

        LocalDateTime localDateTime=LocalDateTime.now();
        Long date=localDateTime.toInstant(ZoneOffset.of("+8")).getEpochSecond();

        User teacher=(User)session.getAttribute("user");

        String Token=date.toString()+"|"+teacher.getuId();

        String text="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxcbf93ad8adfe0f7c&redirect_uri=http://yanhuo.s1.natapp.cc/weixin/auth&response_type=code&scope=snsapi_base&state="+Token +"#wechat_redirect";
        int width = 600;    //二维码图片的宽
        int height = 600;   //二维码图片的高
        String format = "png";  //二维码图片的格式


        response.setContentType("application/octet-stream");

        ServletOutputStream out = response.getOutputStream();

        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = toBufferedImage(bitMatrix);
            ImageIO.write(image, format, out);
            out.flush();
            out.close();

        }catch (Exception e){

        }



    }


}
