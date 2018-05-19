package com.yanhuo.sign.dal.mapper.ext;


import com.yanhuo.sign.dal.DTO.TeacherDTO;
import com.yanhuo.sign.dal.mapper.UserMapper;
import com.yanhuo.sign.dal.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserExtMapper extends UserMapper{

    /**
     * 根据用户名查询用户
     * @param uName
     * @return
     */
    User selectByName(@Param(value = "uName") String uName);

    /**
     * 用户登录
     * @param uName
     * @param uPwd
     * @return
     */
    User login(@Param(value = "uName") String uName, @Param(value = "uPwd") String uPwd);

    /**
     * 查询所有教师信息
     * @return
     */
    List<User> selectAllTeachers();

    /**
     * 根据id查询教师信息
     * @param uId
     * @return
     */
    User selectTeacher(Long uId);

    /**
     * 教师修改登录密码
     * @param user
     * @return
     */
    int updateTeacherInfo(User user);

}