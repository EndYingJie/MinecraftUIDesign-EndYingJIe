package net.endyingjie.ui_desgin.windows.lables;

import net.endyingjie.ui_desgin.manager.interface_manager.SimpleSlotInformation;
import net.endyingjie.ui_desgin.manager.interface_manager.SlotInformation;
import net.endyingjie.ui_desgin.resources.ResourceImageIcon;
import net.endyingjie.ui_desgin.windows.project_main.MenuProjectWindow;
import net.endyingjie.ui_desgin.windows.project_main.SlotType;

import javax.swing.*;
import java.awt.*;

public final class SlotLabel extends JLabel implements SlotInformation {
    private final int index;
    private int x = 0;
    private int y=0;
    private final SlotType type;
    private final MenuProjectWindow window;
    private static final Image image = ResourceImageIcon.create("slots/default_slot.png").getImage().getImage();
    private static final Image inventoryImage = ResourceImageIcon.create("slots/default_inventory_slot.png").getImage().getImage();
    private static final Image outputImage = ResourceImageIcon.create("slots/output_slot.png").getImage().getImage();
    public SlotLabel(int index,SlotType type, MenuProjectWindow window){
        super();
        this.window=window;
        this.setSize((int)(window.getOnePixel()*18),(int)(window.getOnePixel()*18));
        this.index=index;
        this.type = type;
    }
    private SlotLabel(int x,int y,int index,SlotType type,MenuProjectWindow window){
        super();
        this.window=window;
        this.setSize((int)(window.getOnePixel()*18),(int)(window.getOnePixel()*18));
        this.index=index;
        this.x =x;
        this.y = y;
        this.type = type;
        ImageIcon newImage = new ImageIcon(getImage().getScaledInstance((int)(window.getOnePixel()*18),(int)(window.getOnePixel()*18),Image.SCALE_FAST));
        this.setIcon(newImage);
    }
    @Override
    public void setLocation(int x,int y){
        this.x =x;
        this.y =y;
        super.setLocation((int)(window.getOnePixel()*x),(int)(window.getOnePixel()*y));
    }
    public static SlotLabel of(int x,int y,int index,SlotType type,MenuProjectWindow window){
        return new SlotLabel(x,y,index,type,window);
    }
    public static SlotLabel of(SlotInformation slot,MenuProjectWindow window){
        return new SlotLabel(slot.getSlotX(),slot.getSlotY(),slot.getIndex(),slot.getType(),window);
    }
    public SlotInformation getSlotInformation(){
        return new SimpleSlotInformation(x,y,index,type);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        this.setSize((int)(window.getOnePixel()*18),(int)(window.getOnePixel()*18));
        ImageIcon newImage = new ImageIcon(getImage().getScaledInstance((int)(window.getOnePixel()*18),(int)(window.getOnePixel()*18),Image.SCALE_FAST));
        this.newSetLocation(this.x,this.y);
        g.drawImage(newImage.getImage(),0,0,newImage.getImageObserver());
    }
    private Image getImage(){
        switch (this.getType()){
            case DefaultSlot:
                return image;
            case Inventory:
                return inventoryImage;
            case OutPutSlot:
                return outputImage;
            default:
                return image;
        }
    }

    public void newSetLocation(int x,int y){
        this.x = x;
        this.y=y;
        super.setLocation((int)(window.getOnePixel()*x),(int)(window.getOnePixel()*y));
    }
    public void newSetLocation(Point xy){
        this.x = xy.x;
        this.y=xy.y;
        super.setLocation((int)(window.getOnePixel()*xy.x),(int)(window.getOnePixel()*xy.y));
    }
    @Override
    public int getSlotX(){
        return x;
    }
    @Override
    public int getSlotY(){
        return y;
    }
    @Override
    public void setIcon(Icon image){

    }
    @Override
    public int getIndex() {
        return this.index;
    }
    @Override
    public SlotType getType() {
        return type;
    }
}
