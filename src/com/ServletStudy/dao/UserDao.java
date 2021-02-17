package com.ServletStudy.dao;

import com.ServletStudy.entity.Users;
import com.ServletStudy.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

// Data Access Object 数据访问对象
public class UserDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int add(Users user){
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into users(userName, userPwd, sex, email) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2,user.getUserPwd());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());

            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }

        return count;
    }

    public int add(Users user, HttpServletRequest request){
        int count = 0;
        try {
            conn = DBUtil.getConnection(request);
            String sql = "insert into users(userName, userPwd, sex, email) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2,user.getUserPwd());
            ps.setString(3,user.getSex());
            ps.setString(4,user.getEmail());

            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs, request);
        }

        return count;
    }

    public List findAll(){
        List userlist = new ArrayList();
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from users";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                Integer userId = rs.getInt("userId");
                String userName = rs.getString("userName");
                String userPwd = rs.getString("userPwd");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                Users user = new Users(userId, userName, userPwd, sex,email);
                userlist.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }


        return userlist;
    }

    public int delete(String userId){
        int count = 0;

        try {
            conn = DBUtil.getConnection();
            String sql = "delete from users where userId=?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1,Integer.valueOf(userId));
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }

        return count;
    }

    public boolean login(Users user){
        boolean result = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "select * from users where userName=? and userPwd=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,user.getUserName());
            ps.setString(2,user.getUserPwd());

            rs = ps.executeQuery();
            if(rs.next()){result = true;}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }

        return result;
    }
}
