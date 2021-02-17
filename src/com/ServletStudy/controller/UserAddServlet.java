package com.ServletStudy.controller;

import com.ServletStudy.dao.UserDao;
import com.ServletStudy.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

            PrintWriter out = null;
            int result = 0;
            String userName = null;
            String userPwd = null;
            String sex = null;
            String email = null;
            UserDao dao = new UserDao();
            Users user = null;

            userName = req.getParameter("userName");
            userPwd = req.getParameter("userPwd");
            sex = req.getParameter("sex");
            email = req.getParameter("email");

            user = new Users(null, userName, userPwd, sex, email);
            Date startDate = new Date();
            result = dao.add(user,req);
            Date endDate = new Date();
            System.out.println("添加消耗时间："+(endDate.getTime()-startDate.getTime())+"毫秒");
            resp.setContentType("text/html;charset=UTF-8");
            out = resp.getWriter();
            if(result == 1){
                out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
            }else {
                out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
            }

    }
}
