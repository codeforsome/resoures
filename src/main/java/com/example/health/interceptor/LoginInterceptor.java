package com.example.health.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 在请求被处理之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查每个到来的请求对应的session域中是否有登录标识
        Object loginName = request.getSession().getAttribute("loginName");
        if (null == loginName || !(loginName instanceof String)) {
            // 未登录，重定向到登录页
            response.sendRedirect("/admin/login");
            return false;
        }
        return true;
    }

    /**
     * 在请求被处理后，视图渲染之前调用
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
