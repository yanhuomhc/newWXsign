package com.yanhuo.sign.service;


import com.yanhuo.sign.dal.model.StudentInfo;
import com.yanhuo.sign.utils.PageResult;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description:
 * @date 2018/4/15 下午9:52
 */
public interface StudentInfoService {

    PageResult<StudentInfo> selectAllStudentByPage(Long tId, Integer page, Integer limit);
}
