package net.endyingjie.ui_desgin.windows.jdialog_windows.other_window;

import net.endyingjie.ui_desgin.Main;
import net.endyingjie.ui_desgin.resources.LangReading;
import net.endyingjie.ui_desgin.resources.ResourceImageIcon;

import javax.swing.*;
import java.awt.*;

public final class NewNoticeJDialog extends JDialog {
    private static final LangReading lang =LangReading.create(LangReading.Model.LANG);
    public NewNoticeJDialog(){
        super(Main.mainWindows,lang.getContent("new_notice_title"),true);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
        Dimension Diamond = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(Diamond.width/2-375,Diamond.height/2-200,750,400);
        this.setIconImage(ResourceImageIcon.create("button/introduce.png").getImage().getImage());
        JLabel content =new JLabel(lang.getContent("new_notice_content"),JLabel.CENTER);
        content.setFont(new Font(Font.SERIF, Font.BOLD,48));
        content.setForeground(new Color(0x7F3980));
        content.setBackground(new Color(0x3D3D3D));
        JScrollPane contentPane = new JScrollPane(content);
        contentPane.setBackground(new Color(0x3D3D3D));
        this.setResizable(false);
        this.add(contentPane);
    }
}
