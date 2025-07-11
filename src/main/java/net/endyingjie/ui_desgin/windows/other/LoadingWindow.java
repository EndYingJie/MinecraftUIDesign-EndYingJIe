package net.endyingjie.ui_desgin.windows.other;

import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.resources.ResourceReading;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public final class LoadingWindow extends JFrame {
    public final JProgressBar loadBar = new JProgressBar();
    public LoadingWindow(){
        super(JSONObject.parseObject(ResourceReading.create("lang/".concat(Locale.getDefault().getLanguage().concat("_").concat(Locale.getDefault().getCountry().toLowerCase())).concat(".json")).getContent()).getString("loading_window_title"));
        this.setLayout(null);
        this.setResizable(false);
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(size.width/2-200,size.height/2-75,400,150);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
        this.loadBar.setBorderPainted(true);
        this.loadBar.setIndeterminate(true);
        this.loadBar.setBounds(25,this.getHeight()/2-15,this.getWidth()-50,30);
        loadBar.setBackground( new Color(0x474747));
        this.add(loadBar);
        this.setVisible(true);
    }
}
