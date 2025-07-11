package net.endyingjie.ui_desgin.resources;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ResourceDataReading {
    private final String path;
    private final String fileName;
    private String content;
    private final Logger logger = Logger.getLogger("ResourceReading Class");
    private ResourceDataReading(String path){
        this.path = "data/".concat(path);
        this.fileName = this.path.substring(this.path.lastIndexOf("/")+1);
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(ResourceReading.class.getClassLoader().getResourceAsStream(this.path));
            logger.log(Level.INFO,"file is finding");
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
                logger.log(Level.INFO,"file is reading");
            }catch (Exception e){
                logger.log(Level.WARNING,"file is not reading");
            }
        }catch (Exception e){
            logger.log(Level.WARNING,"file is not find");
            this.content =null;
        }
    }
    public String getContent(){
        return this.content;
    }
    public static ResourceDataReading create(String path){
        return new ResourceDataReading(path);
    }
    public String getFileName(){
        return this.fileName;
    }
}
