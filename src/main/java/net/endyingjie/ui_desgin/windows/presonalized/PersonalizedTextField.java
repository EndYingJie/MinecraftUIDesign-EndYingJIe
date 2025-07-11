package net.endyingjie.ui_desgin.windows.presonalized;

import javax.swing.*;
import java.awt.*;

public final class PersonalizedTextField extends JTextField {
    private static final Color color = new Color(0x474747);
    public PersonalizedTextField(){
        super();
        this.setBackground(color);
        this.setForeground(Color.WHITE);
    }
    public PersonalizedTextField(String text){
        super(text);
        this.setBackground(color);
        this.setForeground(Color.WHITE);
    }
}
