package com.rjgc.util;

import javax.swing.*;
import java.awt.*;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/28/21
 * @Time: 1:11 PM
 */
public class DimensionUtil {
    public static Rectangle getBounds(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // 保证主界面不会覆盖到电脑屏幕的任务栏
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        return new Rectangle(screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right,
                screenSize.height - screenInsets.top - screenInsets.bottom);
    }
}
