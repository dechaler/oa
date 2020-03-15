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
        String requestURI = request.getRequestURI();
        System.out.println("请求前拦截：" + requestURI);
        if (null != employee) {
            int role = employee.getRole();
            if (role != 1) {
                if (requestURI.indexOf("manage") != -1) {
                    return false;
                }else {
                    return true;
                }
            }else {
                return true;
            }
        }
        else {
            response.sendRedirect(request.getContextPath() + "/views/login.html");
            return false;
        }
    }
}
