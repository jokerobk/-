package com.rjgc.user.view.ext;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 3:16 PM
 */
public class UserViewCellRender extends DefaultTableCellRenderer {
    @Override
    // 在每一行的每一列显示之前都会调用
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(row % 2 == 0){
            setBackground(Color.LIGHT_GRAY);
        }
        else{
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
