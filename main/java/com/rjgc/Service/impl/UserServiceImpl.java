package com.rjgc.Service.impl;

import com.rjgc.Service.UsersService;
import com.rjgc.request.UserRequest;
import com.rjgc.res.TableDTO;
import com.rjgc.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 8:33 PM
 */
public class UserServiceImpl implements UsersService {
    @Override
    public TableDTO retrieveUser(UserRequest userRequest) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from VIP.user");
        extracted(userRequest, sql);
        sql.append(" order by id asc limit ").append(userRequest.getStart()).append(",").append(userRequest.getPageSize());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO tableDTO = new TableDTO();

        try{
            conn = DBUtil.getConn();
            assert conn != null;
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            // 设置数据
            tableDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from VIP.user");
            extracted(userRequest, sql);
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while(rs.next()){
                // 设置数据数量
                int count = rs.getInt(1);
                tableDTO.setTotalCount(count);
            }
            return tableDTO;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return null;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data = new Vector<>();
        while(rs.next()){
            // 处理查出的每一条记录
            Vector<Object> oneRecord = new Vector<>();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            String num = rs.getString("phoneNum");
            String date = rs.getString("date");
            String level = rs.getString("level");
            String days = rs.getString("days");
            int coachID = rs.getInt("coachId");
            String extend = rs.getString("extend");
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(num);
            oneRecord.addElement(sex);
            oneRecord.addElement(date);
            oneRecord.addElement(level);
            oneRecord.addElement(days);
            oneRecord.addElement(coachID);
            oneRecord.addElement(extend);
            data.addElement(oneRecord);
        }
        return data;
    }

    private void extracted(UserRequest userRequest, StringBuilder sql) {
        if(userRequest.getSearchKey() != null && !"".equals(userRequest.getSearchKey().trim())){
            sql.append(" where name like '%").append(userRequest.getSearchKey().trim()).append("%'");
            sql.append(" or id = '").append( userRequest.getSearchKey().trim()).append("'");
            sql.append(" or sex = '").append( userRequest.getSearchKey().trim()).append("'");
            sql.append(" or phoneNum like '%").append(userRequest.getSearchKey().trim()).append("%'");
            sql.append(" or coachId = '").append(userRequest.getSearchKey().trim()).append("'");
            System.out.println(sql);
        }
    }
}
