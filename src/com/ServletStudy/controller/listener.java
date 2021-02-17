package com.ServletStudy.controller;

import com.ServletStudy.util.DBUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class listener implements ServletContextListener {
    //在Tomcat启动时，预先创建20个Connection，在UserDao.add方法执行时
    //将事先创建好的Connection交给add方法
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Map map = new HashMap();

        for(int i = 1;i<=20;i++){
            Connection conn = null;
            try {
                conn = DBUtil.getConnection();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            System.out.println("启动Http服务器，创建Connection"+conn);
            //true表示该通道处于空闲状态
            map.put(conn,true);
        }
        //保存到全局作用对象中
        ServletContext application = sce.getServletContext();
        application.setAttribute("conn-key",map);
    }
    //在服务器关闭时，将Connection销毁
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map = (Map)application.getAttribute("conn-key");
        Iterator it = map.keySet().iterator();

        while(it.hasNext()){
            Connection conn = (Connection)it.next();
            if (conn != null) {
                System.out.println("服务器关闭，Connection销毁");
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
