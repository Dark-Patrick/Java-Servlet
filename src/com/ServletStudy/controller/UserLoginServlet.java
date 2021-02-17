package com.ServletStudy.controller;

import com.ServletStudy.dao.UserDao;
import com.ServletStudy.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String userName, userPwd;
        UserDao dao = new UserDao();
        Users user = null;

        userName = req.getParameter("userName");
        userPwd = req.getParameter("userPwd");
        user = new Users(null,userName,userPwd,null,null);


        if(dao.login(user)){
            HttpSession session = req.getSession();
            session.setAttribute("user",user);

            //十天免登录
            String tenDay = req.getParameter("tenDay");
            if("ok".equals(tenDay)){
                //创建Cookie对象
                Cookie cookie = new Cookie("username",userName);
                Cookie cookie1 = new Cookie("password",userPwd);
                //设置有效时间
                cookie.setMaxAge(60*60*24*10);
                cookie1.setMaxAge(60*60*2*10);
                //设置关联路径
                cookie.setPath(req.getContextPath());
                /**
                 * ?????????
                 */
                cookie1.setPath(req.getContextPath());
                //??????????????????????
                resp.addCookie(cookie);
                resp.addCookie(cookie1);
            }
            resp.sendRedirect(req.getContextPath() + "/index.html");
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/error.html");
        return;
    }
}
