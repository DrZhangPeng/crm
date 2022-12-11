package com.bjpowernode.bank.mvc;

import com.bjpowernode.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    /**
     * 插入账户信息
     * @param act 账户信息
     * @return 1表示插入成功
     */
    public int insert(Account act){
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into t_act(actno,balance) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,act.getActno());
            ps.setDouble(2,act.getBalance());
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        return count;
    }

    /**
     * 根据主键删除账户
     * @param id 账户
     * @return
     */
    public int deleteById(Long id){
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from t_act where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        return count;
    }

    /**
     * 更新账户
     * @param act  账户
     * @return
     */
    public int update(Account act){
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update t_act set balance = ? , actno = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1,act.getBalance());
            ps.setString(2,act.getActno());
            ps.setLong(3,act.getId());
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,null);
        }
        return count;
    }

    /**
     * 根据账号查询账户
     * @param actno    账号
     * @return
     */
    public Account selectByActno(String actno){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account act = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select id,balance from t_act where actno = ?";
            ps.setString(1,actno);
            rs = ps.executeQuery();
            if (rs.next()){
                long id = rs.getLong("id");
                double balance = rs.getDouble("balance");
                act = new Account();
                act.setId(id);
                act.setActno(actno);
                act.setBalance(balance);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return act;
    }

    /**
     * 获取所有账户
     * @return
     */
    public List<Account> selectAll(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Account> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "select id,actno,balance from t_act";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                long id = rs.getLong("id");
                String actno = rs.getString("actno");
                double balance = rs.getDouble("balance");
                Account account = new Account(id,actno,balance);
                list.add(account);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return list;
    }
}
