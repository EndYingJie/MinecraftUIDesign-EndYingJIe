package net.endyingjie.ui_desgin.windows.presonalized;

import javax.swing.*;
import java.awt.*;

public final class PersonalizedButton extends JButton {
    private static final Color color = new Color(0x474747);
    public PersonalizedButton(){
        super();
        this.setBackground(color);
        this.setForeground(Color.WHITE);
    }
    public PersonalizedButton(String name){
        super(name);
        this.setBackground(color);
        this.setForeground(Color.WHITE);
    }
    public PersonalizedButton(String name,Icon icon){
        super(name,icon);
        this.setBackground(color);
        this.setForeground(Color.WHITE);
    }
    public PersonalizedButton(Icon icon){
        super(icon);
        this.setBackground(color);
        this.setForeground(Color.WHITE);
    }
}
