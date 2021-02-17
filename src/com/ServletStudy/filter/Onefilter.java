package com.ServletStudy.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Onefilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = null;
        //调用请求对象读取请求包中请求行中URI，了解用户访问的资源文件是谁
        String uri = request.getRequestURI();//[/网站名称/资源文件名：/myWeb/login.html....]
        //如果本次请求资源文件与登录相关，应放行
        if(uri.contains("login") || "/myWeb/".equals(uri)){
            System.out.println("filter：login相关");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //如果访问其他资源文件，需要活动Session

        session = request.getSession(false);
        if(session != null && session.getAttribute("user") != null){
            System.out.println("filter：存在会话用户");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        System.out.println("filter：非法访问");
        //拒绝请求
        response.sendRedirect(request.getContextPath() + "/login.html");
    }
}
