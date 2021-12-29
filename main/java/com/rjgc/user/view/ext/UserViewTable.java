package com.rjgc.user.view.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 3:16 PM
 */
public class UserViewTable extends JTable {
    public UserViewTable(){
        // 设置表头
        JTableHeader jTableHeader = getTableHeader();
        jTableHeader.setFont(new Font(null, Font.BOLD, 16));
        jTableHeader.setForeground(Color.RED);
        // 表格体
        setFont(new Font(null, Font.PLAIN, 14));
        setForeground(Color.BLACK);
        setGridColor(Color.BLACK);
        setRowHeight(30);
        // 设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

//    public void setDataModel(MainViewTableModel mainViewTableModel){
//        this.setModel(mainViewTableModel);
//    }

    public void renderRule(){
        Vector<String> columns = UserViewTableModel.getColumns();
        UserViewCellRender render = new UserViewCellRender();
        for (int i = 0; i < columns.size(); i ++){
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);
            if( i == 0){
                column.setPreferredWidth(150);
                column.setMaxWidth(150);
                column.setResizable(false);
            }
        }
    }
}
