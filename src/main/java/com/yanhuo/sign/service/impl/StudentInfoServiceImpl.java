package com.yanhuo.sign.service.impl;

import com.yanhuo.sign.dal.mapper.ext.StudentInfoExtMapper;
import com.yanhuo.sign.dal.model.Course;
import com.yanhuo.sign.dal.model.StudentInfo;
import com.yanhuo.sign.service.StudentInfoService;
import com.yanhuo.sign.utils.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 学生信息逻辑处理类
 * @date 2018/4/14 下午1:14
 */
@Slf4j
@Service
public class StudentInfoServiceImpl implements StudentInfoService{

    @Autowired
    private StudentInfoExtMapper studentInfoExtMapper;


    @Override
    public PageResult<StudentInfo> selectAllStudentByPage(Long tId, Integer page, Integer limit) {

        if (page==null){
            page=1;
        }

        List<StudentInfo> courses= studentInfoExtMapper.selectAllBytIdAndPage((page-1)*limit,limit);
        PageResult pageResult=new PageResult();
        pageResult.setSuccess(Boolean.TRUE);
        pageResult.setCode(0);
        pageResult.setData(courses);
        Integer count= studentInfoExtMapper.selectCoutBytIdAndPage();
        pageResult.setCount(count);

        return pageResult;
    }
}
