package net.endyingjie.ui_desgin.windows.jdialog_windows.config_windows;

import net.endyingjie.ui_desgin.Main;
import net.endyingjie.ui_desgin.resources.LangReading;
import net.endyingjie.ui_desgin.windows.project_main.MenuProjectWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public final class ConfigMenuWindow extends JDialog {
    private static final LangReading lang = LangReading.create(LangReading.Model.LANG);
    public  ConfigMenuWindow(){
        super(Main.mainWindows,lang.getContent("config_menu_window"),true);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
        this.setBounds(Main.mainWindows.getX()+Main.mainWindows.getWidth()/2-150
                ,Main.mainWindows.getY()+Main.mainWindows.getHeight()/2-200
                ,300,400);
        JLabel NameLabel = new JLabel("",JLabel.CENTER);
        JButton rightButton =new JButton();
        NameLabel.setBounds(0,20,50,30);
        NameLabel.setForeground(Color.WHITE);
        NameLabel.setText(lang.getContent("name"));
        JTextField ProjectName = new JTextField();
        ProjectName.setBounds(50,20,200,30);
        JComboBox<VersionForge> versionChoose = new JComboBox<VersionForge>();
        for(VersionForge versionItem:VersionForge.values()){
            versionChoose.addItem(versionItem);
        }
        versionChoose.setForeground(Color.WHITE);
        versionChoose.setBackground(Color.GRAY);
        versionChoose.setBounds(50,60,200,30);
        ProjectName.setBackground(Color.GRAY);
        ProjectName.setForeground(Color.WHITE);
        JLabel versionLabel = new JLabel("",JLabel.CENTER);
        versionLabel.setForeground(Color.WHITE);
        versionLabel.setText(lang.getContent("version_label"));
        versionLabel.setBounds(0,60,50,30);
        rightButton.setBounds(50,100,200,30);
        rightButton.setBackground(Color.GRAY);
        rightButton.setForeground(Color.WHITE);
        rightButton.setText(lang.getContent("right_button"));
        VersionForge version=VersionForge.K1$20$1_1$21$;
        versionChoose.setSelectedItem(VersionForge.K1$20$1_1$21$);
        versionChoose.addItemListener(event->{
            if(event.getStateChange() == ItemEvent.SELECTED && event.getItem() instanceof VersionForge){
                version.setVersion((VersionForge) event.getItem());
            }
        });
            rightButton.addActionListener(event->{
                if(!ProjectName.getText().isEmpty()){
                    try{
                        Integer.parseInt(String.valueOf(ProjectName.getText().toCharArray()[0]));
                        JOptionPane.showMessageDialog(null,lang.getContent("number_not_one"));
                    } catch (Exception e) {
                        boolean is = true;
                        for(char theChar :ProjectName.getText().toCharArray()){
                            if(!Character.isLetterOrDigit(theChar)){
                                is = false;
                                break;
                            }
                        }
                        if(is){
                            setVisible(false);
                            Main.mainWindows.setVisible(false);
                            MenuProjectWindow menuProjectWindow = new MenuProjectWindow(ProjectName.getText(), version, true);
                        }else JOptionPane.showMessageDialog(null,lang.getContent("not_special_char"));
                    }

                }else JOptionPane.showMessageDialog(null,lang.getContent("please_write"));
        });
        this.add(versionLabel);
        this.add(rightButton);
        this.add(NameLabel);
        this.add(versionChoose);
        this.add(ProjectName);
        this.setResizable(false);
        this.setVisible(true);
    }
}
