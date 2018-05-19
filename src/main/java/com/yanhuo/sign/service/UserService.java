package com.yanhuo.sign.service;

import com.yanhuo.sign.dal.DTO.TeacherDTO;
import com.yanhuo.sign.dal.model.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 用户类接口
 * @date 2018/4/14 下午1:09
 */
public interface UserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 用户注册
     * @param user
     */
    int register(User user);

    /**
     * 查询所有教师信息
     * @param
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
     * 根据id删除教师信息
     * @param uId
     * @return
     */
    int deleteTeacher(Long uId);

    /**
     * 教师修改个人信息
     * @param user
     * @return
     */
    int updateTeacherInfo(User user);

}
