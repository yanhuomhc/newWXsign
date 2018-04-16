package com.yanhuo.sign.service;

import com.yanhuo.sign.dal.model.User;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: 用户类接口
 * @date 2018/4/14 下午1:09
 */
public interface UserService {

    /**
     * 用户登录
     * @param uName
     * @param uPwd
     * @return
     */
    User login(String uName, String uPwd);

    /**
     * 用户注册
     * @param user
     */
    int register(User user);

}
