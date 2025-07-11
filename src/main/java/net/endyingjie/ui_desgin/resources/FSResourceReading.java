package net.endyingjie.ui_desgin.resources;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class FSResourceReading {
    private final String path;
    private final String FolderName;
    private final Logger logger = Logger.getLogger("FSResourceReading Class");
    private FSResourceReading(String path){
        this.path = path;
        this.FolderName = this.path.substring(this.path.lastIndexOf("\\")+1);

            logger.log(Level.INFO,"Folder is finding");
            if(path.lastIndexOf(".")==-1){
                logger.log(Level.INFO,"It is a Folder");
            }else logger.log(Level.WARNING,"It is not a Folder");

    }
    public ResourceReading getFile(String fileName){
        return ResourceReading.create(this.path.concat("/").concat(fileName));
    }
    public static FSResourceReading create(String path){
        return new FSResourceReading(path);
    }
    public String getFolderName(){
        return this.FolderName;
    }
}
