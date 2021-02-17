package com.ServletStudy.controller;

import com.ServletStudy.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String userId = null;
        int result = 0;

        userId = req.getParameter("userId");
        UserDao dao = new UserDao();
        result = dao.delete(userId);

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(result == 1){
            out.print("<font style='color:red;font-size:40'>用户信息删除成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息删除失败</font>");
        }


    }
}
