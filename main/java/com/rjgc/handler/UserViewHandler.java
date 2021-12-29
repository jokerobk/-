package com.rjgc.handler;

import com.rjgc.user.view.AddUserView;
import com.rjgc.user.view.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 10:41 PM
 */
public class UserViewHandler implements ActionListener {
    private UserView userView;
    public UserViewHandler(UserView userView){
        this.userView = userView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("增加".equals(text)){
            new AddUserView(userView);
        }else if("修改".equals(text)){

        }else if("删除".equals(text)){

        }else if("查询".equals(text)){
            userView.setPageNow(1);
            userView.reloadTable();
        }else if("上一页".equals(text)){
            userView.setPageNow(userView.getPageNow() - 1);
            userView.reloadTable();
        }else if("下一页".equals(text)){
            userView.setPageNow(userView.getPageNow() + 1);
            userView.reloadTable();
        }
    }
}
