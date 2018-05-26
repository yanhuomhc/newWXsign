package com.yanhuo.sign.controller;

import com.yanhuo.sign.dal.VO.SignResultVO;
import com.yanhuo.sign.dal.VO.SignVO;
import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import com.yanhuo.sign.dal.mapper.ext.TeacherSignRecordExtMapper;
import com.yanhuo.sign.dal.model.Sign;
import com.yanhuo.sign.dal.model.StudentInfo;
import com.yanhuo.sign.dal.model.TeacherSignRecord;
import com.yanhuo.sign.dal.model.User;
import com.yanhuo.sign.service.SignService;
import com.yanhuo.sign.utils.PageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/4/14 下午1:15
 */
@Controller
public class SignController {


    @Autowired
    private StudentInfoExtMapper StudentInfoExtMapper;

    @Autowired
    private TeacherSignRecordExtMapper teacherSignRecordExtMapper;

    @Autowired
    private SignService signService;


    /**
     * 签到明细查询页面
     *
     * @return
     */
    @RequestMapping("selectSignDetail")
    public String selectSignDetail() {
        return "teacher/querySignDetail";
    }

    /**
     * 签到结果汇总页面
     *
     * @return
     */
    @RequestMapping("signResultAccount")
    public String signResultAccount() {
        return "teacher/SignResultAccount";
    }

    /**
     * 课程管理页面
     *
     * @return
     */
    @RequestMapping("courseInfo")
    public String courseInfo() {
        return "teacher/teacherAdminCourseInfo";
    }

    @GetMapping("/getRecord")
    @ResponseBody
    public Object getClass(HttpSession session) {

        User user = (User) session.getAttribute("user");
        TeacherSignRecord teacherSignRecord = teacherSignRecordExtMapper.selectBytId(user.getuId().intValue());
        return teacherSignRecord.getSignRecordNo();

    }


    @GetMapping("/querySignDetail")
    @ResponseBody
    public Object querySignDetail(@RequestParam(value = "course", required = false) String course, @RequestParam(value = "sClass", required = false) Long sClass, @RequestParam(value = "SignNo", required = false) Integer SignNo, @RequestParam(value = "current", required = false) Integer current, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (course == null || course.equals("undefined") || course.equals("")) {
            course = null;
        }

        if (sClass == null || sClass.equals("undefined") || sClass.equals("")) {
            sClass = null;
        }

        if (SignNo == null || SignNo.equals("undefined") || SignNo.equals("")) {
            SignNo = null;
        }

        if (ObjectUtils.isEmpty(current)) {
            current = 1;
        }


        PageResult pageResult = signService.selectSignDetailBytCondition(user.getuId(), course, sClass, SignNo, current);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd:HH:mm:ss");

        List<Sign> signList = pageResult.getData();
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
            StudentInfo studentInfo = StudentInfoExtMapper.selectByPrimaryKey(x.getsId().intValue());
            signVO.setsName(studentInfo.getsName());
            signVO.settId(x.gettId());
            signVO.settName(x.gettName());
            signVOS.add(signVO);
        });

        pageResult.setData(signVOS);
        return pageResult;
    }


    @GetMapping("/getTongji")
    @ResponseBody
    public Object querySignDetail(@Param("SignNo") Integer SignNo, HttpSession session) {

        User user = (User) session.getAttribute("user");


        if (SignNo == null || SignNo.equals("undefined") || SignNo.equals("")) {
            SignNo = null;
        }

        List<Sign> signList = signService.selectSignDetailBysignNum(user.getuId().intValue(), SignNo);


        if (CollectionUtils.isEmpty(signList)){
            return null;
        }


        List<StudentInfo> absenceMen = new ArrayList<>();

        signList.forEach(x -> {
            if (!x.getIsSign()){
               StudentInfo studentInfo= StudentInfoExtMapper.selectByPrimaryKey(x.getsId().intValue());
                absenceMen.add(studentInfo);
            }

        });

        SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD:hh:mm:ss");

        SignResultVO signResultVO = new SignResultVO();
        signResultVO.setAbsenceNum(absenceMen.size());
        signResultVO.setAccountTime(sdf.format(new Date()));
        signResultVO.setCourse(signList.get(0).getcName());
        signResultVO.setLocation(signList.get(0).getcLocation());
        signResultVO.setOrdertime(sdf.format(signList.get(0).getBeginSignTime()));
        signResultVO.setPresentNum(signList.size()-absenceMen.size());
        signResultVO.setRate((double)(signList.size()-absenceMen.size()) /signList.size());
        signResultVO.setStudentInfos(absenceMen);
        signResultVO.setSumNUm(signList.size());


        return signResultVO;
    }


}
