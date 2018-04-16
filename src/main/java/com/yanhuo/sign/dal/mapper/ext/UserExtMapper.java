package com.yanhuo.sign.dal.mapper.ext;


import com.yanhuo.sign.dal.mapper.UserMapper;
import com.yanhuo.sign.dal.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserExtMapper extends UserMapper{

    /**
     * 用户登录
     * @param uName
     * @param uPwd
     * @return
     */
    User login(@Param(value = "uName") String uName, @Param(value = "uPwd") String uPwd);

}