package net.endyingjie.ui_desgin.windows.presonalized;

import javax.swing.*;
import java.awt.*;

public class PersonalizedJFrame extends JFrame {
    public PersonalizedJFrame(String title, GraphicsConfiguration gc){
        super(title, gc);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
    }
    public PersonalizedJFrame(String title){
        super(title);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
    }
    public PersonalizedJFrame(GraphicsConfiguration  gc){
        super(gc);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
    }
    public PersonalizedJFrame(){
        super();
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(0x3D3D3D));
    }
}
