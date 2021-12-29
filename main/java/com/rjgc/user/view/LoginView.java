package com.rjgc.user.view;
import com.rjgc.handler.JTextFieldHintHandler;
import com.rjgc.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * @Author: "下铺死楠彤"
 * @Date: 12/27/21
 * @Time: 4:24 PM
 */
public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("健身中心会员管理系统", JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名:");
    JTextField userTextField = new JTextField();
    JLabel passWordLabel = new JLabel("密码:");
    JPasswordField pwdField = new JPasswordField();
    JButton loginButton = new JButton("登陆");
    JButton resetButton = new JButton("重置");
    // 托盘
    SystemTray systemTray;
    TrayIcon trayIcon;
    LoginHandler loginHandler;
    public LoginView(){
        super("健身中心会员管理系统");
        loginHandler = new LoginHandler(this);
        Container contentPane = getContentPane();
        Font fontNameLabel = new Font("手札体", Font.BOLD, 40);
        Font fontOther = new Font("楷体", Font.PLAIN, 20);
        nameLabel.setFont(fontNameLabel);
        nameLabel.setPreferredSize(new Dimension(0, 80));

        userNameLabel.setFont(fontOther);
        userTextField.setPreferredSize(new Dimension(200, 30));
        passWordLabel.setFont(fontOther);
        pwdField.setPreferredSize(new Dimension(200, 30));
        loginButton.setFont(fontOther);
        resetButton.setFont(fontOther);
        // 把组件加入面板

        centerPanel.add(userNameLabel);
        centerPanel.add(userTextField);
        centerPanel.add(passWordLabel);
        centerPanel.add(pwdField);
        // 按钮监听事件
        loginButton.addActionListener(loginHandler);
        // 增加按键事件
        loginButton.addKeyListener(loginHandler);
        centerPanel.add(loginButton);
        // 按钮监听事件
        resetButton.addActionListener(loginHandler);
        centerPanel.add(resetButton);
        layoutCenter();

        contentPane.add(nameLabel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        // 系统托盘
        if(SystemTray.isSupported()){
            System.out.println("Yes");
            systemTray = SystemTray.getSystemTray();
            ImageIcon trayImageIcon = new ImageIcon("tray.png");
            trayIcon = new TrayIcon(trayImageIcon.getImage());
            // 设置托盘图片自动缩放
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            // 增加最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                    LoginView.this.dispose();
                }
            });
            // 托盘事件监听
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount = e.getClickCount();
                    if(clickCount == 1){
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
                }
            });
        }
        // 设置loginButton为默认按钮
        getRootPane().setDefaultButton(loginButton);
        // 自定义图标
        ImageIcon imageIcon = new ImageIcon("/home/xinong/IdeaProjects/课程设计/src/main/resources/tubiao.png");
        setIconImage(imageIcon.getImage());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void layoutCenter() {
        // 弹簧布局
        // 布局userNameLabel

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userTextField)), Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, userNameLabel, -offsetX, SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, userNameLabel, 20, SpringLayout.NORTH, centerPanel);
        // userTextField
        springLayout.putConstraint(SpringLayout.WEST, userTextField, 20, SpringLayout.EAST, userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, userTextField, 0, SpringLayout.NORTH, userNameLabel);
        // passWordLabel
        springLayout.putConstraint(SpringLayout.NORTH, passWordLabel, 20, SpringLayout.SOUTH, userNameLabel);
        springLayout.putConstraint(SpringLayout.EAST, passWordLabel, 0, SpringLayout.EAST, userNameLabel);
        // pwdField
        springLayout.putConstraint(SpringLayout.WEST, pwdField, 20, SpringLayout.EAST, passWordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pwdField, 0, SpringLayout.NORTH, passWordLabel);
        // loginButton
        springLayout.putConstraint(SpringLayout.WEST, loginButton,0,SpringLayout.WEST,passWordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, 40, SpringLayout.SOUTH, passWordLabel);
        // reseatButton
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 80, SpringLayout.EAST, loginButton);
        springLayout.putConstraint(SpringLayout.SOUTH, resetButton, 0, SpringLayout.SOUTH, loginButton);
    }


    public JTextField getUserTextField() {
        return userTextField;
    }


    public JPasswordField getPwdField() {
        return pwdField;
    }

}
