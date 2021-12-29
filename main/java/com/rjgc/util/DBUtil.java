package com.rjgc.util;

import java.sql.*;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 4:09 PM
 */
public class DBUtil {
    // 数据库驱动
    private static final String Driver = "org.mariadb.jdbc.Driver";
    // url
    private static final String url = "jdbc:mysql://localhost:3306";
    // 用户名
    private static final String user = "root";
    // 密码
    private static final String pass = "105369Zz";

    static {
        try {
            // 加载数据库驱动 静态代码块
            Class.forName(Driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 获取数据库链接
    public static Connection getConn(){
        try {
            return DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void closeConn(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closePs(PreparedStatement ps){
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeRs(ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
