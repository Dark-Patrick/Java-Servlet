package com.ServletStudy.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

/**
 * JDBC工具类，简化JDBC编程
 */

public class DBUtil {
    /**
     * 工具类中的构造方法都是私有的
     * 因为工具类中的方法都是静态的，不需要new对象，直接采用类名调用
     */
    private DBUtil(){}

    //静态代码块在类加载时执行，并且只执行一次
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //抛出异常，返回连接对象 conn 连接对象 ps 数据库操作对象 rs 结果集
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/firstbase?serverTimezone=GMT","root","root");
    }
    //重载
    public static Connection getConnection(HttpServletRequest request)throws SQLException{
        Connection conn = null;
        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("conn-key");
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            conn  = (Connection)it.next();
            boolean flag = (boolean)map.get(conn);
            if(flag == true){
                map.put(conn, false);
                break;
            }
        }
        return conn;
    }

    //关闭资源
    public static void close(Connection conn, Statement ps, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
    //重载
    public static void close(Connection conn,Statement ps, ResultSet rs, HttpServletRequest request){
        if(rs != null){
            try {
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(ps != null){
            try {
                ps.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("conn-key");
        map.put(conn, true);

    }
}
