package net.endyingjie.ui_desgin.write;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public final class SoftwareIntroductionWriter extends CConfigWriter{
    public SoftwareIntroductionWriter(){
        super();
    }
    public void write(){
        try{
            FileWriter fileWriter = new FileWriter(CConfigWriter.configPath);
            if(jsonObject.containsKey("brief_introduction")){
                jsonObject.replace("brief_introduction",true);
            }else jsonObject.put("brief_introduction",true);
            fileWriter.append(jsonObject.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            this.logger.warning("can't read config file");
        }
    }
    public boolean read(){
        if(jsonObject!=null){
           return jsonObject.containsKey("brief_introduction") && jsonObject.getBooleanValue("brief_introduction");
        }else return false;
    }
    @Override
    public Logger getLogger() {
        return Logger.getLogger("Software Introduction Writer");
    }
}
