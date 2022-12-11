package com.bjpowernode.bank.web.servlet;

import com.bjpowernode.bank.exceptions.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/transfer")
public class AccountTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double money = Double.parseDouble(request.getParameter("money"));

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mvc?serverTimezone=UTC";
            String user = "root";
            String password = "3426";
            conn = DriverManager.getConnection(url, user, password);
            String sql1 = "select balance from t_act where actno = ?";
            ps = conn.prepareStatement(sql1);
            ps.setString(1,fromActno);
            rs = ps.executeQuery();
            if (rs.next()){
                double balance = rs.getDouble("balance");
                if (balance < money){
                    throw new MoneyNotEnoughException("对不起，余额不足");
                }
            }
        } catch (Exception e) {
//            e.printStackTrace();
            out.print(e.getMessage());
        }finally {
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
}
