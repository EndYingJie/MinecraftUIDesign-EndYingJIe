package net.endyingjie.ui_desgin.windows.lables;

import net.endyingjie.ui_desgin.resources.ResourceImageIcon;
import net.endyingjie.ui_desgin.windows.project_main.MenuProjectWindow;

import javax.swing.*;
import java.awt.*;

public class SlotGroupLabel extends JLabel implements SlotGroupInformation {
    private int x;
    private int y;
    private final int weightSlot;
    private final int heightSlot;
    private final int index;
    private static final Image slotImage = ResourceImageIcon.create("slots/default_slot.png").getImage().getImage();
    private final MenuProjectWindow window;
    public SlotGroupLabel(SlotGroupInformation slotGroupInformation,MenuProjectWindow window){
        super();
        this.x =slotGroupInformation.getCX();
        this.y =slotGroupInformation.getCY();
        this.weightSlot = slotGroupInformation.getWeightSlot();
        this.heightSlot = slotGroupInformation.getHeightSlot();
        this.index = slotGroupInformation.getIndex();
        this.window = window;
        this.setSize((int)(window.getOnePixel()*18*weightSlot),(int)(window.getOnePixel()*18*this.heightSlot));
        this.setLocation((int)(window.getOnePixel()*this.x),(int)(window.getOnePixel()*this.y));
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        ImageIcon newImage =new ImageIcon( slotImage.getScaledInstance((int)(window.getOnePixel()*18),(int)(window.getOnePixel()*18),2));
        for(int y1=0;y1<this.heightSlot;++y1){
            for(int x1 =0;x1<this.weightSlot;++x1){
                g.drawImage(newImage.getImage(),(int)(x1*18*window.getOnePixel()),(int)(y1*18*window.getOnePixel()),newImage.getImageObserver());
            }
        }
        this.setSize((int)(window.getOnePixel()*18*weightSlot),(int)(window.getOnePixel()*18*this.heightSlot));
        this.setLocation((int)(window.getOnePixel()*this.x),(int)(window.getOnePixel()*this.y));
    }
    public void newSetLocation(int x,int y){
        this.x= x;
        this.y = y;
    }
    public void newSetLocation2(int x,int y){
        this.x= x;
        this.y = y;
        this.locationRepaint();

    }
    public void newSetLocation(Point Location){
        this.x  =Location.x;
        this.y = Location.y;
    }
    public void locationRepaint(){
        this.setLocation((int)(window.getOnePixel()*this.x),(int)(window.getOnePixel()*this.y));
    }
    public void sizeRepaint(){
        this.setSize((int)(window.getOnePixel()*18*weightSlot),(int)(window.getOnePixel()*18*this.heightSlot));
    }
    @Override
    public int getCX() {
        return this.x;
    }

    @Override
    public int getCY() {
        return this.y;
    }

    @Override
    public int getWeightSlot() {
        return this.weightSlot;
    }

    @Override
    public int getHeightSlot() {
        return this.heightSlot;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
}
