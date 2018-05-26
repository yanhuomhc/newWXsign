package com.yanhuo.sign.dal.VO;

import com.yanhuo.sign.dal.model.StudentInfo;

import java.util.List;

public class SignResultVO {

    private  Integer absenceNum; //缺勤人数
    private  Integer presentNum;//出勤人数
    private  Integer SumNUm;//总人数

    private  Double Rate;//出勤率

    private List<StudentInfo>  studentInfos;//缺勤学生名单

    private String AccountTime;//统计时间
    private String Ordertime;//点到时间
    private String Location;//点到地点
    private String Course;//点到课程

    public SignResultVO() {
    }

    public Integer getAbsenceNum() {
        return absenceNum;
    }

    public void setAbsenceNum(Integer absenceNum) {
        this.absenceNum = absenceNum;
    }

    public Integer getPresentNum() {
        return presentNum;
    }

    public void setPresentNum(Integer presentNum) {
        this.presentNum = presentNum;
    }

    public Integer getSumNUm() {
        return SumNUm;
    }

    public void setSumNUm(Integer sumNUm) {
        SumNUm = sumNUm;
    }

    public Double getRate() {
        return Rate;
    }

    public void setRate(Double rate) {
        Rate = rate;
    }

    public List<StudentInfo> getStudentInfos() {
        return studentInfos;
    }

    public void setStudentInfos(List<StudentInfo> studentInfos) {
        this.studentInfos = studentInfos;
    }

    public String getAccountTime() {
        return AccountTime;
    }

    public void setAccountTime(String accountTime) {
        AccountTime = accountTime;
    }

    public String getOrdertime() {
        return Ordertime;
    }

    public void setOrdertime(String ordertime) {
        Ordertime = ordertime;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }
}
