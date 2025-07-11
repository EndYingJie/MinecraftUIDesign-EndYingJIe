package net.endyingjie.ui_desgin.windows.jdialog_windows;

import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.Main;
import net.endyingjie.ui_desgin.resources.LangReading;
import net.endyingjie.ui_desgin.resources.ResourceReading;
import net.endyingjie.ui_desgin.windows.other.LoadingWindow;
import net.endyingjie.ui_desgin.windows.presonalized.PersonalizedButton;
import net.endyingjie.ui_desgin.windows.project_main.MenuProjectWindow;
import net.endyingjie.ui_desgin.windows.project_main_interface_save_project.SimpleMenuProject;

import javax.swing.*;
import java.awt.*;

public final class ModelWindow extends JDialog {
    private static final LangReading lang = LangReading.create(LangReading.Model.LANG);
    private final PersonalizedButton menuProjectButton = new PersonalizedButton();
    private final Color background = new Color(0x292929);
    public ModelWindow(){
        super(Main.mainWindows,lang.getContent("model_window"),true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screenSize.width/2-150,screenSize.height/2-200,300,400);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(background);
        this.menuProjectButton.addActionListener(event->{
            setVisible(false);
            LoadingWindow loadingWindow = new LoadingWindow();
            Main.mainWindows.setVisible(false);
            MenuProjectWindow menuProjectWindow = new MenuProjectWindow(SimpleMenuProject.of(JSONObject.parseObject(ResourceReading.create("model/MenuProjectModel.json").getContent())));
            loadingWindow.setVisible(false);
        });
        menuProjectButton.setText(lang.getContent("menu_project_title"));
        menuProjectButton.setBounds(50,20,200,30);
        this.add(menuProjectButton);
    }
}
