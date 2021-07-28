package com.xl.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author XLong
 * @create 2021-07-23 17:46
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String URLPath = httpServletRequest.getRequestURI();
        System.out.println("=====URLPath======"+URLPath);
        if (URLPath.endsWith("userLogin")) {
            //放行 后续请求
            return true;
        }
        //判断当前session中是否有值，有值表示登录了，可以往后走
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user_session")!=null) {
            return true;
        } else {
//            httpServletRequest.setAttribute("message","您尚未登录,请登录");
            //拦截后 给出指示 跳转到login
            httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/loginForm.jsp").forward(httpServletRequest,httpServletResponse);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
