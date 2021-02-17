package com.ServletStudy.controller;

import com.ServletStudy.dao.UserDao;
import com.ServletStudy.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class CheckLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String userName = null;
        String userPwd = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                if("username".equals(cookieName))
                    userName = cookieValue;
                else if("password".equals(cookieName))
                    userPwd = cookieValue;
            }
        }
        System.out.println("检查流程：1");
        if(userName != null && userPwd != null){
            Users user = new Users(null,userName,userPwd,null,null);
            UserDao dao = new UserDao();

            if(dao.login(user)){
                System.out.println("Login from cookies");
                HttpSession session = req.getSession();

                session.setAttribute("user",user);
                resp.sendRedirect(req.getContextPath() + "/index.html");
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/error.html");
            return;
        }
        else {

            resp.sendRedirect(req.getContextPath() + "/login.html");
            System.out.println("无cookies登录");
        }
    }
}
