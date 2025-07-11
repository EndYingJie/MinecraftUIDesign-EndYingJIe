package net.endyingjie.ui_desgin.windows.jdialog_windows.other_window;

import net.endyingjie.ui_desgin.Main;
import net.endyingjie.ui_desgin.resources.LangReading;
import net.endyingjie.ui_desgin.resources.ResourceImageIcon;
import net.endyingjie.ui_desgin.write.SoftwareIntroductionWriter;

import javax.swing.*;
import java.awt.*;

public final class SoftwareIntroductionWindow extends JDialog {
    private static final LangReading lang =LangReading.create(LangReading.Model.LANG);
    public SoftwareIntroductionWindow(){
        super(Main.mainWindows,lang.getContent("software_introduction"),true);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
        Dimension Diamond = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(Diamond.width/2-500,Diamond.height/2-300,1000,600);
        SoftwareIntroductionWriter temp= new SoftwareIntroductionWriter();
        this.setIconImage(ResourceImageIcon.create("button/introduce.png").getImage().getImage());
        JLabel content =new JLabel(lang.getContent("software_introduction_content"),JLabel.CENTER);
        content.setFont(new Font(Font.SERIF, Font.BOLD,48));
        content.setForeground(new Color(0x7F3980));
        content.setBackground(new Color(0x3D3D3D));
        JScrollPane contentPane = new JScrollPane(content);
        contentPane.setBackground(new Color(0x3D3D3D));
        this.setResizable(false);
        this.add(contentPane);
        if(!Main.introduce){
            temp.write();
            Main.mainWindows.setIntroduceNotShow();
            this.setVisible(true);
        }
    }
}
