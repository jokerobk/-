package com.rjgc.user.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 3:16 PM
 */
public class UserViewTableModel extends DefaultTableModel {
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("会员编号");
        columns.addElement("姓名");
        columns.addElement("联系方式");
        columns.addElement("性别");
        columns.addElement("会员办理日期");
        columns.addElement("会员等级");
        columns.addElement("会员时长");
        columns.addElement("教练编号");
        columns.addElement("备注");
    }
    private UserViewTableModel(){
        super(null, columns);
    }
    private static UserViewTableModel userViewTableModel = new UserViewTableModel();
    public static UserViewTableModel assembleModel(Vector<Vector<Object>> data){
        userViewTableModel.setDataVector(data, columns);
        return userViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>> data){
        userViewTableModel.setDataVector(data, columns);

    }
    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
