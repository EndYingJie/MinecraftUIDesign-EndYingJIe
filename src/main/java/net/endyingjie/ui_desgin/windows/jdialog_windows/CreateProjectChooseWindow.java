package net.endyingjie.ui_desgin.windows.jdialog_windows;

import net.endyingjie.ui_desgin.Main;
import net.endyingjie.ui_desgin.resources.LangReading;
import net.endyingjie.ui_desgin.windows.jdialog_windows.config_windows.ConfigMenuWindow;

import javax.swing.*;
import java.awt.*;

public final class CreateProjectChooseWindow extends JDialog {
    private static final LangReading Lang = LangReading.create(LangReading.Model.LANG);
    public CreateProjectChooseWindow(){
        super(Main.mainWindows,Lang.getContent("creating_project_window"),true);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
        this.setResizable(false);
        this.setBounds(Main.mainWindows.getX()+Main.mainWindows.getWidth()/2-150
                ,Main.mainWindows.getY()+Main.mainWindows.getHeight()/2-200
                ,300,400);
        JButton createMenu = new JButton(Lang.getContent("create_menu"));
        createMenu.setBounds(50,20,200,30);
        createMenu.setBackground(new Color(0x98BCE3));
        createMenu.addActionListener(event->{
            setVisible(false);
            ConfigMenuWindow configMenuWindow = new ConfigMenuWindow();
        });
        this.add(createMenu);
        this.setLayout(null);
        this.setVisible(true);
    }
}
