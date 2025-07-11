package net.endyingjie.ui_desgin.resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ResourceImageIcon {
    private ImageIcon imageIcon;
    private static final Logger logger = Logger.getLogger("Resource ImageIcon");
    private ResourceImageIcon(String path){
        try{
            BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("assets/textures/".concat(path)));
            this.imageIcon = new ImageIcon(image);
        } catch (IOException e) {
            logger.log(Level.WARNING,"load image is error");
        }
    }
    public static ResourceImageIcon create(String path){
        return new ResourceImageIcon(path);
    }
    public ImageIcon getImage(){
        return imageIcon;
    }
}
