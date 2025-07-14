package net.endyingjie.ui_desgin.windows;

import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.Main;
import net.endyingjie.ui_desgin.configs.Config;
import net.endyingjie.ui_desgin.resources.LangReading;
import net.endyingjie.ui_desgin.resources.ResourceImageIcon;
import net.endyingjie.ui_desgin.windows.jdialog_windows.CreateProjectChooseWindow;
import net.endyingjie.ui_desgin.windows.jdialog_windows.ModelWindow;
import net.endyingjie.ui_desgin.windows.jdialog_windows.SettingWindow;
import net.endyingjie.ui_desgin.windows.jdialog_windows.config_windows.VersionForge;
import net.endyingjie.ui_desgin.windows.jdialog_windows.other_window.NewNoticeJDialog;
import net.endyingjie.ui_desgin.windows.jdialog_windows.other_window.SoftwareIntroductionWindow;
import net.endyingjie.ui_desgin.windows.presonalized.PersonalizedButton;
import net.endyingjie.ui_desgin.windows.project_main.MenuProjectWindow;
import net.endyingjie.ui_desgin.windows.project_main.ProjectTypes;
import net.endyingjie.ui_desgin.windows.project_main_interface_save_project.SimpleMenuProject;
import net.endyingjie.ui_desgin.write.NewNoticeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public final class MainWindows extends JFrame {
    private static final LangReading lang =LangReading.create(LangReading.Model.LANG);
    private final JLayeredPane InitialPanel = new JLayeredPane();
    private final JButton createButton= new JButton();
    private final JLabel version = new JLabel();
    private final JButton importJSONProject= new JButton();
    private final JLabel InitialBackGround1 = new JLabel();
    private final JLabel InitialBackGround2 = new JLabel();
    private final ArrayList<Runnable> ClosingEventsAll = new ArrayList<>();
    private final JButton introduceButton = new JButton(ResourceImageIcon.create("button/introduce.png").getImage());
    private final JButton SettingButton = new JButton(ResourceImageIcon.create("button/setting.png").getImage());
    private final JLabel LogoTextures = new JLabel();
    private final JButton ModelChoose = new JButton();
    public MainWindows(){
        super(lang.getContent("MainWindowTitle"));
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                for(Runnable runnable :ClosingEventsAll){
                    runnable.run();
                }
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        this.ModelChoose.addActionListener(event->{
            ModelWindow modelWindow = new ModelWindow();
            modelWindow.setVisible(true);
        });
        this.ModelChoose.setBackground(new Color(0x292929));
        this.ModelChoose.setIcon(ResourceImageIcon.create("button/model.png").getImage());
        this.ModelChoose.setForeground(Color.WHITE);
        this.importJSONProject.setText(lang.getContent("import_json_project"));
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                LogoTextures.setLocation(getWidth()/2-470,32);
                InitialBackGround1.setSize(InitialPanel.getWidth(),200);
                InitialBackGround2.setSize(InitialPanel.getWidth(),InitialPanel.getHeight()-200);
                version.setLocation(InitialPanel.getWidth()/2+300,150);
                createButton.setLocation(InitialPanel.getWidth()/2-300,210);
                importJSONProject.setLocation(InitialPanel.getWidth()/2-300,270);
                ModelChoose.setLocation(InitialPanel.getWidth()/2+310,210);
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
        importJSONProject.setIcon(ResourceImageIcon.create("button/import_json_button.png").getImage());
        FileDialog file =new FileDialog(new Frame(),"import_json_project",0);

        importJSONProject.addActionListener(event->{
            Thread thread =new Thread(()->{
                String con;
                StringBuffer temp = new StringBuffer();
                file.setVisible(true);
                while(true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        Logger.getLogger("Main Window").warning("sleep Error");
                    }

                    if(!file.isVisible()){
                        try {
                            try {
                                try{
                                    BufferedReader reader = new BufferedReader(new FileReader(file.getDirectory().concat(file.getFile())));
                                    while ((con = reader.readLine()) != null) {
                                        temp.append(con);
                                    }
                                    con = temp.toString();
                                    JSONObject json = JSONObject.parseObject(con);
                                    this.setVisible(false);
                                    switch (ProjectTypes.valueOf(json.getString("project_type"))){
                                        case MenuProject:
                                            SimpleMenuProject project = SimpleMenuProject.of(json);
                                            MenuProjectWindow menuProjectWindow = new MenuProjectWindow(project);
                                            break;

                                    }
                                    break;
                                }catch (NullPointerException e){
                                    Logger.getLogger("Main Window").warning("Not Found Your Project JSON file");
                                    e.printStackTrace();
                                    break;
                                }
                            } catch (FileNotFoundException e) {
                                Logger.getLogger("Main Window").warning("Not Found Project JSON file");
                                break;
                            }
                        } catch (IOException e) {
                            Logger.getLogger("Main Window").warning("Not Found");
                            break;
                        }
                    }
                }

            });
            thread.start();
        });
        this.setIconImage(ResourceImageIcon.create("icon.png").getImage().getImage());
        importJSONProject.setBackground(new Color(0x292929));
        importJSONProject.setFont(new Font(Font.SERIF, Font.BOLD,24));
        importJSONProject.setForeground(Color.WHITE);
        importJSONProject.setSize(600,50);
        ModelChoose.setSize(50,50);
        this.InitialPanel.add(ModelChoose);
        this.InitialPanel.add(importJSONProject);
        Dimension Diamond=Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocale(Config.lang);
        LogoTextures.setBounds(getWidth()/2-470,getHeight()/2-75,921,150);
        this.setBounds(Diamond.width/2-500,Diamond.height/2-300,1000,600);
        this.setMinimumSize(new Dimension(941,450));
        this.LogoTextures.setIcon(ResourceImageIcon.create("logo.png").getImage());
        if(!Main.introduce){
            introduceButton.setBounds(115, 125, 50, 50);
            introduceButton.setBackground(new Color(0x292929));
            introduceButton.addActionListener(event -> {
                SoftwareIntroductionWindow introductionWindow = new SoftwareIntroductionWindow();
            });
            Logger.getLogger("Main Window").info("start prepare introduce");
            this.InitialPanel.add(introduceButton);
        }else Logger.getLogger("Main Window").info("Don't prepare introduce");
        this.InitialBackGround1.setOpaque(true);
        this.InitialBackGround1.setBackground(new Color(0x545454));
        this.InitialBackGround1.setBounds(0,0,this.InitialPanel.getWidth(),200);
        this.InitialPanel.add(this.InitialBackGround1,new Integer(0));
        this.InitialBackGround2.setBackground(new Color(0x3D3D3D));
        this.InitialBackGround2.setOpaque(true);
        this.InitialBackGround2.setLocation(0,200);
        this.version.setSize(200,50);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
        this.version.setFont(new Font(Font.SERIF,Font.BOLD,16));
        this.version.setForeground(Color.WHITE);
        this.version.setText(lang.getContent("VersionTip").concat(Config.TheVersion));
        this.setForeground(Color.WHITE);
        this.InitialPanel.add(this.InitialBackGround2,new Integer(0));
        this.InitialPanel.add(this.LogoTextures,new Integer(1));
        this.InitialPanel.add(version,new Integer(1));
        this.InitialPanel.setLayout(null);
        this.createButton.setBackground(new Color(0x292929));
        this.createButton.setIcon(ResourceImageIcon.create("button/create_button.png").getImage());
        this.SettingButton.setBounds(50,125,50,50);
        this.SettingButton.addActionListener(event->{
            SettingWindow settingWindow = new SettingWindow();
        });
        this.createButton.addActionListener(event->{
            CreateProjectChooseWindow createProjectChooseWindow = new CreateProjectChooseWindow();
        });
        NewNoticeWriter writer= new NewNoticeWriter();
        NewNoticeJDialog newNoticeJDialog=null;
        if(!writer.read()){
            newNoticeJDialog = new NewNoticeJDialog();
            writer.write();
        }

        this.SettingButton.setBackground(new Color(0x292929));
        this.InitialPanel.add(this.SettingButton,new Integer(2));
        this.createButton.setSize(600,50);
        this.createButton.setText(lang.getContent("CreateProject"));
        this.createButton.setForeground(Color.WHITE);
        this.createButton.setFont(new Font(Font.SERIF, Font.BOLD,24));
        this.InitialPanel.add(this.createButton,new Integer(1));
        this.add(InitialPanel);
        Main.loadWindow.setVisible(false);
        setVisible(true);
        if(newNoticeJDialog!=null){
            newNoticeJDialog.setVisible(true);
        }
    }

    public void addClosingEvent(Runnable runnable){
        this.ClosingEventsAll.add(runnable);
    }
    public void setIntroduceNotShow(){
        this.introduceButton.setVisible(false);
    }
    public void setInitialPaneVisible(boolean aFlag){
        this.InitialPanel.setVisible(aFlag);
    }
}
