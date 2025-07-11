package net.endyingjie.ui_desgin.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ResourceReading {
    private final String path;
    private final String fileName;
    private String content;
    private final Logger logger = Logger.getLogger("ResourceReading Class");
    private ResourceReading(String path){
        this.path = "assets/".concat(path);
        this.fileName = this.path.substring(this.path.lastIndexOf("/")+1).concat(" ");
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(ResourceReading.class.getClassLoader().getResourceAsStream(this.path), StandardCharsets.UTF_8);
            logger.log(Level.INFO,fileName.concat("file is finding"));
            try{
                BufferedReader buf = new BufferedReader(inputStreamReader);
                String con;
                StringBuffer con_buf = new StringBuffer();
                while((con= buf.readLine())!=null){
                    con_buf.append(con);
                }
                con = con_buf.toString();
                this.content = con;
                inputStreamReader.close();
                buf.close();
                logger.log(Level.INFO,fileName.concat("file is reading"));
            }catch (IOException e){
                logger.log(Level.WARNING,fileName.concat("file is not reading"));
            }
        }catch (Exception e){
            logger.log(Level.WARNING,fileName.concat("file is not find"));
            this.content =null;
        }
    }
    public String getContent(){
        return this.content;
    }
    public static ResourceReading create(String path){
        return new ResourceReading(path);
    }
    public String getFileName(){
        return this.fileName;
    }
}
