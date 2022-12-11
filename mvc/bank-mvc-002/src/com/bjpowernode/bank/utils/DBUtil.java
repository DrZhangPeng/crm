package com.bjpowernode.bank.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC工具类
 * @author 张鹏
 * @version 1.0
 * @since 1.0
 */
public class DBUtil {

    private static ResourceBundle bundle = ResourceBundle.getBundle("resources/jdbc");
    private static final String driver = bundle.getString("driver");
    private static final String url = bundle.getString("url");
    private static final String user = bundle.getString("user");
    private static final String password = bundle.getString("password");


    //不让创建对象，工具类方法都是静态的
    private DBUtil(){}

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url,user,password);
        return connection;
    }

    public static void close(Connection conn, Statement ps, ResultSet rs){
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
