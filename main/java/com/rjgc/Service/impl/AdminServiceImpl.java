package com.rjgc.Service.impl;

import com.rjgc.Service.AdminService;
import com.rjgc.entity.AdminDO;
import com.rjgc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 4:22 PM
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validateAdmin(AdminDO adminDO) {
        String userName = adminDO.getUserName();
        String pwdParam = adminDO.getPwd();
        if(userName == null || "".equals(userName.trim())){
            return false;
        }
        String sql = "select pwd from VIP.manager where userName = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBUtil.getConn();
            if (conn == null){
                return false;
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, adminDO.getUserName());
            rs = ps.executeQuery();
            while(rs.next()){
                String pwd = rs.getString(1);
                if (pwdParam.equals(pwd)){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                DBUtil.closeRs(rs);
                DBUtil.closePs(ps);
                DBUtil.closeConn(conn);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return false;
    }
}
