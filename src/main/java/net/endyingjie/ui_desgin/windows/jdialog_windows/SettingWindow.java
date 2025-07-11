package net.endyingjie.ui_desgin.windows.jdialog_windows;

import net.endyingjie.ui_desgin.Main;
import net.endyingjie.ui_desgin.configs.Config;
import net.endyingjie.ui_desgin.configs.LangConfig;
import net.endyingjie.ui_desgin.resources.LangReading;
import net.endyingjie.ui_desgin.resources.ResourceImageIcon;
import net.endyingjie.ui_desgin.windows.presonalized.PersonalizedButton;
import net.endyingjie.ui_desgin.write.LangWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;

public final class SettingWindow extends JDialog {
    private static final LangReading lang =LangReading.create(LangReading.Model.LANG);
    private final JPanel ChooseJPanel = new JPanel();
    private final JButton LangChoose = new JButton();
    private final JPanel languageJPanel = new JPanel();
    private final PersonalizedButton LangRight = new PersonalizedButton();
    private LangConfig theLang;
    private final JComboBox<LangConfig> langChooseButton = new JComboBox<LangConfig>();
    private final JScrollPane choosePanel = new JScrollPane(languageJPanel);
    private static final LangWriter lang_writer = new LangWriter();
    public SettingWindow(){
        super(Main.mainWindows,lang.getContent("setting"),true);
        this.setBounds(Main.mainWindows.getX()+Main.mainWindows.getWidth()/4,Main.mainWindows.getY()+Main.mainWindows.getHeight()/4, Main.mainWindows.getWidth()/2,Main.mainWindows.getHeight()/2);
        this.setLayout(null);
        this.setMinimumSize(new Dimension(Main.mainWindows.getWidth()/2,Main.mainWindows.getHeight()/2));
        this.ChooseJPanel.setBounds(0,0,125,Toolkit.getDefaultToolkit().getScreenSize().height*2+30);
        this.ChooseJPanel.setBackground(Color.GRAY);
        this.ChooseJPanel.setLayout(null);
        LangChoose.setBackground(new Color(0x474747));
        LangChoose.setForeground(Color.WHITE);
        LangChoose.setFont(new Font(Font.DIALOG, Font.BOLD,16));
        LangChoose.setText(lang.getContent("language"));
        LangChoose.setBounds(0,0,125,50);
        this.langChooseButton.setBounds(20,20,100,30);
        this.languageJPanel.setLayout(null);
        LangChoose.addActionListener(event->{
            choosePanel.add(this.languageJPanel);
        });
        choosePanel.setBounds(125,0,this.getWidth()-125,this.getHeight());
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                choosePanel.setBounds(125,0,getWidth()-125,getHeight());
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
        for(LangConfig lang :LangConfig.values()){
            this.langChooseButton.addItem(lang);
        }
        this.langChooseButton.setBackground(new Color(0x474747));
        this.langChooseButton.setForeground(Color.WHITE);
        this.langChooseButton.addItemListener(event->{
            if(event.getStateChange()==ItemEvent.SELECTED) {
                this.theLang= (LangConfig) event.getItem();
            }
        });
        this.LangRight.addActionListener(event->{
            switch (theLang){
                case Chinese:
                    lang_writer.write("zh","CN");
                    Config.lang_enum=LangConfig.Chinese;
                    theLang = LangConfig.English;
                    break;
                case English:
                    lang_writer.write("en","US");
                    Config.lang_enum=LangConfig.English;
                    theLang = LangConfig.English;
                    break;
            }
            JOptionPane.showMessageDialog(null,lang.getContent("lang_set_tip"));
        });
        this.setIconImage(ResourceImageIcon.create("button/setting.png").getImage().getImage());
        this.theLang = Config.lang_enum;
        this.LangRight.setIcon(ResourceImageIcon.create("button/right.png").getImage());
        this.langChooseButton.setSelectedItem(this.theLang);
        this.languageJPanel.setBackground(new Color(0x2B2B2B));
        this.LangRight.setBounds(140,20,30,30);
        this.languageJPanel.add(this.LangRight);
        this.languageJPanel.add(this.langChooseButton);
        this.ChooseJPanel.add(this.LangChoose);
        this.add(choosePanel);
        this.add(ChooseJPanel);
        this.setVisible(true);
    }
}
