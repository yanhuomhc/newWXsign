package com.yanhuo.sign.utils;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author 烟火（yanhuo@maihaoche.com）
 * @version V1.0
 * @Description: TODO
 * @date 2018/3/27 上午10:12
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws IOException {
        HttpSession session = request.getSession();
        //判断是否已有该用户登录的session
        //if (session.getAttribute(SESSION_KEY) != null) {
        //    return true;
       // }
        //跳转到登录页
        String url = "/login";
        response.sendRedirect(url);
        return false;
    }

}
