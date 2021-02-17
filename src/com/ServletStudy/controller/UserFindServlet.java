package com.ServletStudy.controller;

import com.ServletStudy.dao.UserDao;
import com.ServletStudy.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);


        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(session != null){
            Users user = (Users)session.getAttribute("user");
            //Mike 是超级账户，有权访问
            if(user.getUserName().equals("Mike")){
                UserDao dao = new UserDao();
                List<Users> userlist = dao.findAll();

                out.print("<table border='2' align='center'>");
                out.print("<tr>");
                out.print("<td>用户编号</td>");
                out.print("<td>用户姓名</td>");
                out.print("<td>用户密码</td>");
                out.print("<td>用户性别</td>");
                out.print("<td>用户邮箱</td>");
                out.print("<td>操作</td>");
                out.print("</tr>");
                for(Users users:userlist){
                    out.print("<tr>");
                    out.print("<td>" + users.getUserId() + "</td>");
                    out.print("<td>" + users.getUserName() + "</td>");
                    out.print("<td>" + users.getUserPwd() + "</td>");
                    out.print("<td>" + users.getSex() + "</td>");
                    out.print("<td>" + users.getEmail() + "</td>");
                    out.print("<td><a href='/myWeb/user/delete?userId="+users.getUserId()+"'>删除用户</a></td>");
                    out.print("</tr>");
                }
                out.print("</table>");
                return;
            }
            out.print("<font style='color:red;font-size:40'>该账户无权访问</font>");
            return;
        }
        out.print("<font style='color:red;font-size:40'>会话已过期，请重新登录</font>");

    }

}
