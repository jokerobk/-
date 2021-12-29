package com.rjgc.user.view;

import com.rjgc.Service.UsersService;
import com.rjgc.Service.impl.UserServiceImpl;
import com.rjgc.handler.UserViewHandler;
import com.rjgc.request.UserRequest;
import com.rjgc.res.TableDTO;
import com.rjgc.user.view.ext.UserViewTable;
import com.rjgc.user.view.ext.UserViewTableModel;
import com.rjgc.util.DimensionUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 1:07 PM
 */
public class UserView extends JFrame {
    JPanel northJPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addButton = new JButton("增加");
    JButton updateButton = new JButton("修改");
    JButton delButton = new JButton("删除");
    JTextField searchTextField = new JTextField(15);
    JButton searchButton = new JButton("查询");
    JPanel southJPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");

    UserViewTable userViewTable = new UserViewTable();
    // 设置页码
    private int pageNow = 1;    // 当前第几页
    private int pageSize = 10;  // 一页显示多少条数据库记录

    UserViewHandler userViewHandler;

    public UserView(){
        super("主界面-健身中心会员管理系统");
        Container contentPane = getContentPane();
        userViewHandler = new UserViewHandler(this);
        // 放置北边的组件
        layoutNorth(contentPane);
        // 放置中间的组件
        layoutCenter(contentPane);
        // 放置南边的组件
        layoutSouth(contentPane);

        // 自定义图标
        ImageIcon imageIcon = new ImageIcon("/home/xinong/IdeaProjects/课程设计/src/main/resources/tubiao.png");
        setIconImage(imageIcon.getImage());

        // 根据屏幕设置主界面的大小
        setBounds(DimensionUtil.getBounds());
        // 设置窗体完全充满整个屏幕的可见大小
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void layoutCenter(Container contentPane) {
        TableDTO tableDTO = getTableDTO();
        UserViewTableModel userViewTableModel = UserViewTableModel.assembleModel(tableDTO.getData());


        userViewTable.setModel(userViewTableModel);
        userViewTable.renderRule();
        JScrollPane jScrollPane = new JScrollPane(userViewTable);
        contentPane.add(jScrollPane, BorderLayout.CENTER);
        showPreNext(tableDTO.getTotalCount());
    }




    private void layoutSouth(Container contentPane) {
        southJPanel.add(preButton);
        southJPanel.add(nextButton);
        // 增加事件监听
        preButton.addActionListener(userViewHandler);
        nextButton.addActionListener(userViewHandler);
        contentPane.add(southJPanel, BorderLayout.SOUTH);
    }
    // 设置上一页是否可见
    private void showPreNext(int totalCount){
        preButton.setVisible(pageNow != 1);
        int pageCount = 0;  // 总共有多少页
        if(totalCount % pageSize == 0){
            pageCount = totalCount / pageSize;
        }
        else{
            pageCount = totalCount / pageSize + 1;
        }
        nextButton.setVisible(pageNow != pageCount);
    }

    private void layoutNorth(Container contentPane) {
        northJPanel.add(addButton);
        northJPanel.add(updateButton);
        northJPanel.add(delButton);
        northJPanel.add(searchTextField);
        northJPanel.add(searchButton);
        // 增加事件监听
        addButton.addActionListener(userViewHandler);
        updateButton.addActionListener(userViewHandler);
        delButton.addActionListener(userViewHandler);
        searchButton.addActionListener(userViewHandler);
        contentPane.add(northJPanel, BorderLayout.NORTH);
    }

    private TableDTO getTableDTO() {
        UsersService usersService = new UserServiceImpl();
        UserRequest userRequest = new UserRequest();
        userRequest.setPageNow(pageNow);
        userRequest.setPageSize(pageSize);
        userRequest.setSearchKey(searchTextField.getText().trim());
        return usersService.retrieveUser(userRequest);
    }

    public void reloadTable(){
        TableDTO tableDTO = getTableDTO();
        UserViewTableModel.updateModel(tableDTO.getData());
        userViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }
}
