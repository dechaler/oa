package com.de.interceptor;

import com.de.entity.Employee;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @编写人:de
 * @时间:2019/10/25
 * @描述:拦截器
 */
public class MyInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        if (null != employee)
            return true;
        else
            return false;
    }
}
